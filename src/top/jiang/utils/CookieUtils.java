package top.jiang.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	public static String getRoomID(Cookie[] cookies){
		for(Cookie c : cookies){
    		if(c.getName().equals("roomID")){
    			return c.getValue();
    		}
    	
    	}
		return null;
	}
	public static String getVisitorID(Cookie[] cookies){
		for(Cookie c : cookies){
    		if(c.getName().equals("visitorID")){
    			return c.getValue();
    		}
    	}
		return null;
	}
}
