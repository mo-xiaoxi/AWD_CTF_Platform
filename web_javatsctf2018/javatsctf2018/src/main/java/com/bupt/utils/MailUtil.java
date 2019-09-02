package com.bupt.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class MailUtil {
    private static final String PROPERTIES_DEFAULT = "sysConfig.properties";
    private static Properties props;
    

    static {
    	props = new Properties();
        try{
            InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            props.load(inputStream);
            inputStream.close();
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }
    
    /**
     * @param to 接收方地址
     * @param title 邮件标题
     * @param content 邮件内容
     * @throws Exception 
     */
    public static void sendMail(String to,String title,String content)throws Exception{
    	final String username = props.getProperty("mail.username");
    	final String password = props.getProperty("mail.password");
    	Session session = Session.getInstance(props,new Authenticator() {
    		 public PasswordAuthentication getPasswordAuthentication(){
    			    return new PasswordAuthentication(username, password);
            }
        });
    	//2.创建邮件对象
    	Message message = new MimeMessage(session);
    	//2.1 设置发件人
    	message.setFrom(new InternetAddress(username));
    	//2.2设置收件人
    	message.addRecipient(Message.RecipientType.CC, new InternetAddress(username));
    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    	//2.3设置主题
    	message.setSubject(title); // 加载标题  
    	//2.4 设置内容
    	message.setContent(content, "text/html;charset=utf-8");
    	// 3.创建 Transport用于将邮件发送
    	Transport.send(message);
    }
}