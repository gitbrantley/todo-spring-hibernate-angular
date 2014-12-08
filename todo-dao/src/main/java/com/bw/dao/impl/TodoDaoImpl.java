package com.bw.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bw.dao.TodoDao;
import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Todo;
import com.bw.hibernate.entity.TodoItem;

@Repository("todoDao")
@Transactional(readOnly=true)
public class TodoDaoImpl extends AbstractDao implements TodoDao {
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Todo> list(TodoCriteria cri) {
		Criteria c = session().createCriteria(Todo.class, "t");
		if (cri.isIncludeItems()) {
			c.setFetchMode("items", FetchMode.JOIN);
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		}
		List<Todo> list = c.list();
		return list;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	@Override
	public boolean update(Todo todo) {
		Session sesh = session();
		if (todo.getId() > 0) {
			for (TodoItem item : todo.getItems()) {
				item.setTodo(todo);
				if (item.getId() > 0) {
					sesh.update(item);
				} else {
					sesh.save(item);
				}
			}
			sesh.update(todo);
		} else {
			throw new IllegalArgumentException("Todo missing id");
		}
		return false;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	@Override
	public long create(Todo todo) {
		Session sesh = session();
		Serializable id = sesh.save(todo);
		if (id != null) {
			for (TodoItem item : todo.getItems()) {
				item.setTodo(todo);
				if (item.getId() > 0) {
					sesh.update(item);
				} else {
					sesh.save(item);
				}
			}
		}
		return (Long) id;
	}
}
