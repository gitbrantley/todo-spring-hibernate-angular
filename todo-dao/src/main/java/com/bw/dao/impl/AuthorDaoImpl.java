package com.bw.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bw.dao.AuthorDao;
import com.bw.hibernate.entity.Author;

@Repository("authorDao")
@Transactional(readOnly=true)
public class AuthorDaoImpl extends AbstractDao implements AuthorDao {
	private static final String PASSWORD_SALT = "todo_app_salt";
	
	private String hashPassword(String password) {
		String original = PASSWORD_SALT+":"+password;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not resolve MD5 algorithm: "+e.getMessage());
		}
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
	
	
	public Author getByUsername(String un, boolean includeTodos) {
		Criteria c = session().createCriteria(Author.class, "a");
		if (includeTodos) {
			c.setFetchMode("todos", FetchMode.JOIN);
		}
		c.add(Restrictions.ilike("username", un));
		Author a = (Author) c.uniqueResult();
		return a;
	}
	
	public long update(Author a) {
		return 0;
	}
	
	public long create(Author a) {
		return 0;
	}
	
}
