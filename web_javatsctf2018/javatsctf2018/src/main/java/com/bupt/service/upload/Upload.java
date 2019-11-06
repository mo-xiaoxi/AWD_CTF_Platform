package com.bupt.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class Upload {  
      
    /* 
     */
    public String uploadImage(MultipartFile file, String realUploadPath, String uuidFileName) throws IOException
    {  
        File uploadFile=new File(realUploadPath+"/rawImages");
        if(!uploadFile.exists()){  
            uploadFile.mkdirs();  
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        ;
        InputStream inputStream=file.getInputStream();
        String outputPath=realUploadPath+"/rawImages/"+uuidFileName+"."+suffix;
        OutputStream outputStream=new FileOutputStream(outputPath);
        byte[] buffer=new byte[1024];
          
        while((inputStream.read(buffer))>0)
        {  
          outputStream.write(buffer);  
        }  
        outputStream.close();  
        return "images/rawImages/"+uuidFileName+"."+suffix;
    }  
  
}  
