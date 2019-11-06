package com.bupt.service.upload;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class Thumbnail {  
      
    public static final int witdth=150;
    public static final int heigth=150;
      
    /*       
     */
    public String generateThumbnail(MultipartFile file, String realUploadPath,String uuidFileName) throws IOException
    {
        File uploadFile=new File(realUploadPath+"/thumbImages");
        if(!uploadFile.exists()){  
            uploadFile.mkdirs();  
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        String des=realUploadPath+"/thumbImages/"+uuidFileName+"."+suffix;
        Thumbnails.of(file.getInputStream()).size(witdth, heigth).toFile(des);
        return "images/thumbImages/"+uuidFileName+"."+suffix;
    }  
  
}  