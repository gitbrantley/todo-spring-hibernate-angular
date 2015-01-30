package com.bw.web.todo.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
	
	public static TodoUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof TodoUser) {
			return (TodoUser)auth.getPrincipal();
		}
		throw new AccessDeniedException("No user credentials exist");
	}
	
}
