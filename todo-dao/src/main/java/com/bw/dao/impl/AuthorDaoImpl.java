package com.bw.dao.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bw.dao.AuthorDao;
import com.bw.hibernate.entity.Author;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

@Repository("authorDao")
@Transactional(readOnly=true)
public class AuthorDaoImpl extends AbstractDao implements AuthorDao {
	private static final String PASSWORD_SALT = "todo_app_salt";
	
	private static String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	
	public Author getByUsername(String un, boolean includeTodos) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> query = cb.createQuery(Author.class);
		Root<Author> author = query.from(Author.class);
		Path<String> usernamePath = author.get("username");
		if (includeTodos) {
			//c.setFetchMode("todos", FetchMode.JOIN);
		}
		query.where(cb.like(usernamePath, un));
		Author a = (Author) em.createQuery(query).getSingleResult();
		return a;
	}
	
	public long update(Author a) {
		return 0;
	}
	
	public long create(Author a) {
		return 0;
	}

}
