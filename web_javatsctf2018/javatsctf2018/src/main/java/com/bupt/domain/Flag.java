package com.bupt.domain;

import java.io.*;

public class Flag {
    public String flag = "Null";
    public String path = "/flag/flag";

    public static String flag2String(String path){
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public void setFlag(){
        flag = flag2String(path);
    }

    public String getFlag(){
        return flag.trim();
    }
}

