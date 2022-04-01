package com.group.projectwork.utility;

import javax.servlet.http.HttpSession;

import com.group.projectwork.entity.Utente;

public class SessionUtils {
	
	private SessionUtils() {}
	
	public static void sessionCleanup(HttpSession session) {
		var user = session.getAttribute("utente");
		
		//check if there's any trace of "broken session" left and removes it in case there is 
		if (user != null && (!(user instanceof Utente) || ((Utente) user).getToken() == null))
			session.setAttribute("utente", null);
	}
}
