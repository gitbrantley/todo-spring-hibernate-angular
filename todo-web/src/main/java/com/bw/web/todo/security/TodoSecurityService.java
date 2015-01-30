package com.bw.web.todo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bw.dao.AuthorDao;
import com.bw.hibernate.entity.Author;

public class TodoSecurityService implements UserDetailsService {
	
	@Autowired
	private AuthorDao authorDao;

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Author auth = authorDao.getByUsername(username, false);
		if (auth == null) {
			return null;
		}
		return new TodoUser(auth, Arrays.asList(new SimpleGrantedAuthority("ROLE_TODOS_GET")));
	}
	
	
}
