package com.bupt.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 */
public class SysConfigUtils {
    public static String noipLink;
    public static String ctfLink;
    public static String releaseState;
    public static String ctfEmail;

    private static final String PROPERTIES_DEFAULT = "sysConfig.properties";
    private static Properties props;

    /**
     */
    static {
        props = new Properties();
        try{
            InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            props.load(inputStream);
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        noipLink = props.getProperty("competition.link.noip");
        ctfLink = props.getProperty("competition.link.ctf");
        releaseState = props.getProperty("competition.link.releaseState","false");
        ctfEmail = props.getProperty("system.public.ctf.email","");
    }
}
