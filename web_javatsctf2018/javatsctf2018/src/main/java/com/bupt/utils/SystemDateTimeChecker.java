package com.bupt.utils;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SystemDateTimeChecker {

	public static final String from;
	public static final String to;

	public static final String emailFrom;
	public static final String emailTo;
	public static final String format;
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
		from = props.getProperty("system.start.time");
		to = props.getProperty("system.end.time");
		emailFrom = props.getProperty("system.emailOperation.start.time");
		emailTo = props.getProperty("system.emailOperation.end.time");
		format = props.getProperty("system.time.format");
	}
	/**
	 */
	public static boolean checkNowOk() throws ParseException {
		Date current = new Date(System.currentTimeMillis());
		Date fDate  = new SimpleDateFormat(format).parse(from);
		Date tDate  = new SimpleDateFormat(format).parse(to);

		System.out.println("From："+fDate.getTime());
		System.out.println("Current："+current.getTime());
		System.out.println("To："+tDate.getTime());
		
		System.out.println(current.getTime()-fDate.getTime());
		System.out.println(tDate.getTime()-current.getTime());
		
		if(current.getTime()-fDate.getTime()>0&&tDate.getTime()-current.getTime()>0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 */
	public static boolean checkEmailOperationAvailable() throws ParseException {
		Date current = new Date(System.currentTimeMillis());
		Date fDate  = new SimpleDateFormat(format).parse(emailFrom);
		Date tDate  = new SimpleDateFormat(format).parse(emailTo);

		System.out.println("email From："+fDate.getTime());
		System.out.println("email Current："+current.getTime());
		System.out.println("email To："+tDate.getTime());

		System.out.println(current.getTime()-fDate.getTime());
		System.out.println(tDate.getTime()-current.getTime());

		if(current.getTime()-fDate.getTime()>0&&tDate.getTime()-current.getTime()>0) {
			return true;
		}else {
			return false;
		}
	}
}
