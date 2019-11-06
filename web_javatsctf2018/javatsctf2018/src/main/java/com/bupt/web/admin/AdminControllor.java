package com.bupt.web.admin;

import com.bupt.dao.ProfileDao;
import com.bupt.dao.UserDao;
import com.bupt.domain.Profile;
import com.bupt.domain.User;
import com.bupt.utils.ProfileExcelExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminControllor {
    @Autowired
    ProfileDao profileDao;
    @Autowired
    UserDao userDao;

    @RequestMapping("/excel.html")
    public void excel(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("-->admin/excel.html");
        String username = (String) session.getAttribute("username");
        User temp = userDao.findUserByUsername(username);
        System.out.println(temp);
        if (temp.getFirst() != User.ADMIN) {
            return;
        }
        System.out.println(session.getServletContext().getRealPath(""));
        String path = session.getServletContext().getRealPath("") + File.separator + "excel";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        List<Profile> profileDaoList = profileDao.findAll();
        String filePath = path + File.separator + "恋爱的季节.xls";
        ProfileExcelExportUtils.generateExcel(filePath, profileDaoList);

        try {
            res.setHeader("Content-disposition", "attachment;fileName=" + new String("恋爱的季节.xls".getBytes("GBK"), "ISO8859-1"));
            res.setContentType("application/vnd.ms-excel;charset=UTF-8");
            FileInputStream input = new FileInputStream(filePath);
            OutputStream out = res.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            res.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
        } catch (Exception ex) {
        }
        return;
    }
}
