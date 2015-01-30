package com.bw.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bw.dao.AuthorDao;
import com.bw.hibernate.entity.Author;

@Repository("authorDao")
@Transactional(readOnly=true)
public class AuthorDaoImpl extends AbstractDao implements AuthorDao {
	private static final String PASSWORD_SALT = "todo_app_salt";
	
	private static String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
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
	
	public static void main(String[] args) {
		System.out.println(AuthorDaoImpl.hashPassword("brantley"));
	}
}
