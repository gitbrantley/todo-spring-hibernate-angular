package com.bw.dao;

import com.bw.hibernate.entity.Author;

public interface AuthorDao {
	
	Author getByUsername(String un, boolean includeTodos);
	long update(Author a);
	long create(Author a);
}
