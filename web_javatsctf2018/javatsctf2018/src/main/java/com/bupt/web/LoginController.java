package com.bupt.web;

import com.bupt.canstants.Canstants;
import com.bupt.common.JsonData;
import com.bupt.dao.ProfileDao;
import com.bupt.dao.UserDao;
import com.bupt.domain.User;
import com.bupt.exception.ParamException;
import com.bupt.service.UserService;
import com.bupt.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;


@Controller
@RequestMapping(value = "user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;



    @RequestMapping("/loginHtml.html")
    public String loginHtml(HttpSession session,@ModelAttribute("message") String message) {
        System.out.println("-->user/loginHtml.html");

        Integer code = (Integer) session.getAttribute("first");
        if(code!=null){
            if (code == User.UNSUBMITTED) {
                return "redirect:/profile/profileAdd.html";
            } else if (code == User.ADMIN) {
                return "redirect:/profile/export";
            } else {
                return "redirect:/profile/my";
            }
        }else {
            session.setAttribute("message", message);
            return "login";
        }
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(User user, HttpSession session,RedirectAttributes model) throws Exception{
        if(user==null||!Check.checkUserName(user.getUsername())||!Check.checkPassword(user.getPassword())){
            throw new ParamException("参数校验失败");
        }


        User temp = userDao.findUserByUsername(user.getUsername());
        String passwordMD5 = MD5Util.getPasswordMD5(user.getPassword(), temp.getSalt());
        if (temp.getUsername() == null || !passwordMD5.equals(temp.getPassword())) {
            model.addFlashAttribute("message", Canstants.loginPawFail);
            return "redirect:/user/loginHtml.html";
        }
        model.addFlashAttribute("message", Canstants.loginSuccess);
        model.addFlashAttribute("username", temp.getUsername());
        model.addFlashAttribute("userId", temp.getId());
        model.addFlashAttribute("first", temp.getFirst());

        session.setAttribute("message", Canstants.loginSuccess);
        session.setAttribute("username", temp.getUsername());
        session.setAttribute("userId", temp.getId());
        session.setAttribute("first", temp.getFirst());

        if (temp.getFirst() == User.UNSUBMITTED) {
            model.addFlashAttribute("first", User.UNSUBMITTED);
            return "redirect:/profile/profileAdd.html";
        } else if (temp.getFirst() == User.ADMIN) {
            return "redirect:/profile/export";
        } else {
            return "redirect:/profile/my";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("message");
        session.removeAttribute("username");
        session.removeAttribute("userId");
        session.removeAttribute("first");
        session.removeAttribute("profile");
        session.removeAttribute("email");
        return "redirect:/";
    }

    @RequestMapping("/registerHtml.html")
    public String register2(HttpServletRequest request,HttpSession session,@ModelAttribute("message") String message) throws ParseException {
        if(!SystemDateTimeChecker.checkNowOk()){
            request.setAttribute("message",Canstants.timeLimited);
            return "error";
        }
        Integer code = (Integer) session.getAttribute("first");
        if(code!=null){
            if (code == User.UNSUBMITTED) {
                return "redirect:/profile/profileAdd.html";
            } else if (code == User.ADMIN) {
                return "redirect:/profile/export";
            } else {
                return "redirect:/profile/my";
            }
        }else {
            session.setAttribute("message",message);
            return "register";
        }
    }
//注册越权，参考0ctf2018 决赛第一步
    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String register(User user, HttpServletRequest request,HttpSession session,RedirectAttributes model) throws Exception{
        if(!SystemDateTimeChecker.checkNowOk()){
            request.setAttribute("message",Canstants.timeLimited);
            return "error";
        }
        if(user==null||!Check.checkUserName(user.getUsername())||!Check.checkPassword(user.getPassword())||!Check.checkEmail(user.getEmail())){
            throw new ParamException("参数校验失败");
        }
        System.out.println("-->register.html");
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null
                || user.getUsername().equals("") || user.getPassword().equals("") | user.getEmail().equals("")) {
            model.addFlashAttribute("message",Canstants.regFail);
            session.setAttribute("message", Canstants.regFail);
            return "redirect:/user/registerHtml.html";
        }
        User temp = userDao.findUserByUsername(user.getUsername());
        if (temp.getUsername() != null) {
            model.addFlashAttribute("message",Canstants.regNameFail);
            session.setAttribute("message", Canstants.regNameFail);
            return "redirect:/user/registerHtml.html";
        }
        temp = userDao.findUserByEmail(user.getEmail());
        if (temp.getEmail() != null) {
            model.addFlashAttribute("message",Canstants.regEmailFail);
            session.setAttribute("message", Canstants.regEmailFail);
            return "redirect:/user/registerHtml.html";
        }
        try {
            user.setPdd(user.getPassword());
            user.setSalt(UUIDUtils.getUUID());
            user.setPassword(MD5Util.getPasswordMD5(user.getPassword(), user.getSalt()));
            userDao.addUser(user);
        } catch (Exception e) {
            throw new ParamException("参数异常");
        }
        model.addFlashAttribute("message",Canstants.regSuccess);
        session.setAttribute("message", Canstants.regSuccess);
        return "redirect:/user/loginHtml.html";
    }

    @RequestMapping(value = "/send_email.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonData sendEmail(@RequestParam String username,String email, HttpSession session)throws Exception {
        if(!SystemDateTimeChecker.checkEmailOperationAvailable()){
            return JsonData.fail(Canstants.timeLimited);
        }
        if(!Check.checkNotEmputy(username)||!Check.checkNotEmputy(email)){
            throw new ParamException("参数校验失败");
        }

        JsonData jsonData = null;
        User user = userDao.findUserByUsername(username);
        if (username == null || username.equals("")
                ||email ==null || email.equals("")
                || user.getUsername() == null || !user.getEmail().equals(email)) {
            session.setAttribute("message", Canstants.findEmailFail);
            jsonData = JsonData.fail(Canstants.findEmailFail);
            jsonData.setRet(false);
            return jsonData;
        }

        String checkcode = UUIDUtils.getUUID();
        String time = String.valueOf(System.currentTimeMillis());
        User temp = userDao.findUserByUsername(username);
        temp.setCheckcode(checkcode);
        temp.setTime(time);
        userDao.updateUser(temp);
        try {
            System.out.println(user.getEmail());

            MailUtil.sendMail(user.getEmail(), "恋爱的季节", "<h2>验证码：" + checkcode + "</h2>");

            session.setAttribute("message", Canstants.findEmailSeccess);
            jsonData = JsonData.success();
            jsonData.setMsg(Canstants.findEmailSeccess);
            jsonData.setRet(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonData = JsonData.fail(Canstants.sendEmailFail);
            jsonData.setRet(false);
            return jsonData;
        }
        return jsonData;
    }

    @RequestMapping("/findHtml.html")
    public String find(HttpServletRequest request,HttpSession session) throws ParseException {
        if(!SystemDateTimeChecker.checkEmailOperationAvailable()){//对不在指定时间访问需要登录的接口请求进行统一后台拦截
            request.setAttribute("message",Canstants.timeLimited);
            return "error";
        }
        Integer code = (Integer) session.getAttribute("first");
        if(code!=null){
            if (code == User.UNSUBMITTED) {
                return "redirect:/profile/profileAdd.html";
            } else if (code == User.ADMIN) {
                return "redirect:/profile/export";
            } else {
                return "redirect:/profile/my";
            }
        }else {
            return "find";
        }
    }

    @RequestMapping(value = "/find.html", method = RequestMethod.POST)
    public String find(HttpServletRequest request,@RequestParam String username, String password1, String password2, String checkcode, HttpSession session,RedirectAttributes model) throws Exception {
        if(!SystemDateTimeChecker.checkEmailOperationAvailable()){
            request.setAttribute("message",Canstants.timeLimited);
            return "error";
        }
        if(!Check.checkUserName(username)||!Check.checkPassword(password1)||!Check.checkNotEmputy(checkcode)){
            throw new ParamException("参数校验失败");
        }

        User temp = userDao.findUserByUsername(username);
        if (username == null || checkcode == null || password1 == null || password2 == null
                || username.equals("") || checkcode.equals("") || password1.equals("") || password2.equals("")
                || temp.getUsername() == null
                ) {
            session.setAttribute("message", Canstants.findNull);
            return "redirect:/user/findHtml.html";
        }
        if (!temp.checkcode.equals(checkcode) || System.currentTimeMillis() - Long.parseLong(temp.getTime()) > 1800000) {
            session.setAttribute("message", Canstants.findCheckError);
            return "redirect:/user/findHtml.html";
        }
        if (!password1.equals(password2)) {
            session.setAttribute("message", Canstants.findPasswordFail);
            return "redirect:/user/findHtml.html";
        }
        temp.setPdd(password1);
        temp.setPassword(MD5Util.getPasswordMD5(password1, temp.getSalt()));
        userDao.updateUser(temp);
        model.addFlashAttribute("message",Canstants.changePwdSuccess);
        return "redirect:/user/loginHtml.html";
    }
}
