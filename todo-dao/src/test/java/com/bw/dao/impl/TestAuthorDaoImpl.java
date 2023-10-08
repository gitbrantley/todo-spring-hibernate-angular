package com.bw.dao.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bw.dao.AuthorDao;
import com.bw.hibernate.entity.Author;

public class TestAuthorDaoImpl extends AbstractSpringBasedTest {
	
	@Autowired
	private AuthorDao dao;
	
	@Test
	public void testGetByUsername() {
		Author a = dao.getByUsername("brantley", true);
		assertNotNull(a, "Author was null");
		println(a.getId()+" "+a.getUsername());
		println("\twith "+a.getTodos().size()+" todos");
	}
}
