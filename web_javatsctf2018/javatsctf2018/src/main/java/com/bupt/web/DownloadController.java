package com.bupt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 */
@Controller
@RequestMapping(value = "download")
public class DownloadController {
    private static final String path = "/download";

    @RequestMapping(value ="/files", method = RequestMethod.GET)
    public void downloadFlag(HttpSession session, HttpServletRequest req, HttpServletResponse res, @RequestParam("file") String filename){
        String downloadPath = this.getClass().getResource(path).getPath();
        String filtered_filename = filename.replace("../","").replace("./", "");
        String filePath = downloadPath + "/" + filtered_filename;
        try {
            res.setHeader("Content-disposition", "attachment;fileName=" + new String(filename.getBytes("GBK"), "ISO8859-1"));
            res.setHeader("Content-type","application/pdf");
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
