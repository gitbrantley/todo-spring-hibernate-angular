package com.bw.web.todo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bw.hibernate.entity.Author;

public class TodoUser implements UserDetails {
	
	private static final long serialVersionUID = -2721566212795646299L;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	private long authorId;
	private String password;
	
	public TodoUser(Author author, Collection<? extends GrantedAuthority> authorities) {
		this.username = author.getUsername();
		this.authorId = author.getId();
		this.password = author.getPasswordEncoded();
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public long getAuthorId() {
		return authorId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
