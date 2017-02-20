package com.zlw.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class CookieHelper {

	private static HttpServletResponse response = ServletActionContext.getResponse();

	public static void saveCookie(String name, String value, int time) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}

}
