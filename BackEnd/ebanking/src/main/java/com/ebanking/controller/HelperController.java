package com.ebanking.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelperController {
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpSession session;
	
	@Autowired
	protected ServletContext application;
	
	protected Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	protected String getCookieValue(String name,String...defaultValue) {
		Cookie cookie = getCookie(name);
		if(cookie==null) {
			return defaultValue[0];
		}
		return cookie.getValue();
	}
	protected void addCookie(Cookie cookie, int days) {
		cookie.setMaxAge(days*24*60*60*60);
		response.addCookie(cookie);
	}
	protected void addCookie(String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		addCookie(cookie, days);
	}
	protected void removeCookie(String name){
		this.addCookie(name, "",0);
	}
}
