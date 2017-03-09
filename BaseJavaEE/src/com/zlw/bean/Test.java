package com.zlw.bean;

import com.zlw.utils.JsonTools;

public class Test {

	public static void main(String[] args) {

		User user = new User();
		user.setUsername("zlw2");
		user.setPaw("123123");
		String json = JsonTools.toJson(user);
		System.out.println(json);
	}
}
