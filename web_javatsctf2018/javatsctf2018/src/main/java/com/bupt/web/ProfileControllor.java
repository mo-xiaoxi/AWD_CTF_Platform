package com.bupt.web;

import com.bupt.common.SpelView;
import com.bupt.canstants.Canstants;
import com.bupt.dao.ProfileDao;
import com.bupt.dao.UserDao;
import com.bupt.domain.Flag;
import com.bupt.domain.Profile;
import com.bupt.domain.User;
import com.bupt.exception.PermissionException;
import com.bupt.service.upload.Thumbnail;
import com.bupt.service.upload.Upload;
import com.bupt.utils.Check;
import com.bupt.utils.SystemDateTimeChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

@Controller
@RequestMapping("/profile")
public class ProfileControllor {
    @Autowired
    private Upload upload;
    @Autowired
    private Thumbnail thumbnail;

    @Autowired
    ProfileDao profileDao;

    @Autowired
    UserDao userDao;

    @RequestMapping("/info.html")
    public String info(){
        return "arrangeMent";
    }

    @RequestMapping("/competitionRule.html")
    public String competitionRule(){
        return "competitionRule";
    }

    @RequestMapping("/profileEditHtml.html")
    public String profileEditPage(HttpSession session){
        Integer first = (Integer) session.getAttribute("first");
        if(first==1) {
            return "profile_edit";
        }else {
            session.setAttribute("message","请先填写报名信息！");
            return "error";
        }
    }

    @RequestMapping("/profileAdd.html")
    public String profileAddPage(HttpServletRequest request,HttpSession session) throws ParseException {
        if(!SystemDateTimeChecker.checkNowOk()){
            request.setAttribute("message",Canstants.timeLimited);
            return "error";
        }
        String username = (String) session.getAttribute("username");
        User temp = userDao.findUserByUsername(username);
        session.setAttribute("email",temp.getEmail());
        return "profile_add";
    }

    /**
     */
    @RequestMapping("/myProfile")
    public String myProfilePage(HttpSession session,@ModelAttribute("message") String message) {
        Integer first = (Integer) session.getAttribute("first");
        if(first!=null&&first==0){
            session.setAttribute("message",Canstants.fillTheProfileFirst);
            return "noProfile";
        }else if(first==1){
            Integer userid = (Integer) session.getAttribute("userId");
            Profile profile = profileDao.findProfileByUserId(userid);
            String template = profileView(profile);//这个函数存在spel注入
            session.setAttribute("Template", template);
            session.setAttribute("profile", profile);
            session.setAttribute("message", message);
            return "profile_view";
        }else if(first==2){
            return "excel";
        }else {
            session.setAttribute("message",Canstants.authFail);
            return "error";
        }
    }

    /**
     */
    @RequestMapping("/my")
    public String myInfo(HttpSession session){
        Integer userid = (Integer) session.getAttribute("userId");
        Profile profile = profileDao.findProfileByUserId(userid);
        session.setAttribute("profile", profile);
        return "arrangeMent";
    }
    @RequestMapping("/export")
    public String exportPage(HttpSession session,@ModelAttribute("message") String message){
        Integer code = (Integer) session.getAttribute("first");
        if(code!=null&&code== User.ADMIN){
            session.setAttribute("message", message);
            Flag flag = new Flag();
            flag.setFlag();
            session.setAttribute("flag", flag.getFlag());
            return "excel";
        }else {
            session.setAttribute("message", Canstants.authFail);
            return "error";
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@RequestParam("image")MultipartFile file, Profile profile, HttpServletRequest request, HttpSession session) throws IOException, ParseException {
        if(!SystemDateTimeChecker.checkNowOk()){//对不在指定时间访问需要登录的接口请求进行统一后台拦截
            request.setAttribute("message","接口暂未开放访问权限");
            return "error";
        }
        boolean flag = Check.checkProfile(profile);
        if(!flag) {
            System.out.println("[-]Check Profile Wrong");
            session.setAttribute("message", Canstants.checkFail);
            return "redirect:/profile/profileAdd.html";
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!Canstants.suffixs.contains(suffix)){//不在允许的后缀之内
            System.out.println("[-] There is something wromng");
            session.setAttribute("message", Canstants.checkFail);
            return "redirect:/profile/profileAdd.html";
        }
        Integer code = (Integer) session.getAttribute("first");
        if (code != null && code == User.UNSUBMITTED) {//
            String realUploadPath = request.getServletContext().getRealPath("/") + "images";
            String uuidFileName = java.util.UUID.randomUUID().toString();
            String imageUrl = upload.uploadImage(file, realUploadPath, uuidFileName);
            String thumbImageUrl = thumbnail.generateThumbnail(file, realUploadPath, uuidFileName);

            int userId = (Integer) session.getAttribute("userId");
            profile.setUser_id(userId);
            profile.setPhoto(thumbImageUrl);
            profileDao.addProfile(profile);
            userDao.updateFirst(userId,1);
            String template = profileView(profile);
            session.setAttribute("Template", template);
            session.setAttribute("profile", profile);

            session.setAttribute("first",1);
            return "redirect:/profile/myProfile";
        } else {
            session.setAttribute("message", Canstants.authFail);
            return "error";
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(Profile profile, HttpServletRequest request, HttpSession session)throws IOException{
        boolean flag = Check.checkProfile(profile);
        if(!flag) {
            session.setAttribute("message", Canstants.checkFail);
            return "redirect:/profile/profileEditHtml.html";
        }
        Integer code = (Integer) session.getAttribute("first");
        if (code != null && code == User.UNSUBMITTED) {
            return "redirect:/profile/myProfile";
        } else if(code != null && code == User.SUBMITTED)  {
            User mailUser = userDao.findUserByEmail(profile.getMail());
            int userId = (Integer) session.getAttribute("userId");
            if(mailUser.getEmail()!=null&&mailUser.getId()!=userId){
                session.setAttribute("message", Canstants.regEmailFail);
                return "redirect:/profile/profileEditHtml.html";
            }
            Profile p = profileDao.findProfileByUserId(userId);
            profile.setId(p.getId());
            profile.setUser_id(p.getUser_id());
            profile.setPhoto(p.getPhoto());
            if(p.getId()!=profile.getId()||p.getUser_id()!=profile.getUser_id()){//越权访问
                session.setAttribute("message", Canstants.authFail);
                return "redirect:/profile/profileEditHtml.html";
            }
            if(userId==profile.getUser_id()){
                profileDao.editProfile(profile);
                userDao.updateEmail(userId,profile.getMail());
            }else {
                throw new PermissionException(Canstants.authFail);
            }
            session.setAttribute("profile", profile);
            return "redirect:/profile/myProfile";
        }else {
            session.setAttribute("message", Canstants.authFail);
            return "error";
        }
    }

    public String profileView(Profile profile) {
        return String.valueOf(new SpelView(profile).getTemplate());
    }
   }
