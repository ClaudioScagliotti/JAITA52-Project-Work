package com.group.projectwork.utility;

import org.springframework.ui.Model;

public class ErrorUtils {

	private ErrorUtils() {}
	
	public static String genericErrorMVC(Model model, String message) {
		model.addAttribute("message", message);
		return "error";
	}
	
	public static String accessDeniedMVC(Model model) {
		return genericErrorMVC(model, "access denied");
	}
	
	public static String paramErrorMVC(Model model) {
		return genericErrorMVC(model, "param error");
	}
}

