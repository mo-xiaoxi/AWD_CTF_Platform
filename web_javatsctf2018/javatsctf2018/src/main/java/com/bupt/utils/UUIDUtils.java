package com.bupt.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").substring(10).toUpperCase();
	}

}
