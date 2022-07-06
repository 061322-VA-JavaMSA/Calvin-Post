package com.revature.util;

import java.util.regex.Pattern;

public class Validate {

	public static boolean isEmail(String s) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return Pattern.compile(regexPattern)
				.matcher(s)
				.matches();
	}
}
