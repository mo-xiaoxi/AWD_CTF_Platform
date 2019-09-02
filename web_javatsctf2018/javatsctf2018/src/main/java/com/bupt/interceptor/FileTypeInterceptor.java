package com.bupt.interceptor;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 */
public class FileTypeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception {
        boolean flag= true;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = 
                    (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files =
                                       multipartRequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                String formKey = (String) iterator.next();
                MultipartFile multipartFile = 
                              multipartRequest.getFile(formKey);
                String filename=multipartFile.getOriginalFilename();
                System.out.println("fileSize:"+multipartFile.getSize());
                if (! checkFile(filename)) {
                    request.setAttribute("message", "不支持的文件类型！");
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp")
                    .forward(request, response); 
                    flag= false;
                } else if(multipartFile.getSize()>4*1024*1000){//文件不能超过4M
                    System.out.println("fileSize:"+multipartFile.getSize());
                    request.setAttribute("message", "上传文件大小超过4M的大小限制！");
                    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp")
                            .forward(request, response);
                    flag= false;
                }
            }
        }
        return flag;
    }
    /**
     */
    private boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,png,jpeg";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
}