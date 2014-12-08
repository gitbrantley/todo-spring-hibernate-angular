package com.bw.dao;

import java.util.List;

import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Todo;

public interface TodoDao {
	public List<Todo> list(TodoCriteria criteria);
	public boolean update(Todo todo);
	public long create(Todo todo);
}
