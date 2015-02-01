package com.bw.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bw.dao.TodoDao;
import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Author;
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
		if (cri.isIncludeAuthor()) {
			c.setFetchMode("author", FetchMode.JOIN);
		}
		if (cri.getAuthorId() > 0) {
			c.createAlias("t.author", "a");
			c.add(Restrictions.eq("a.id", cri.getAuthorId()));
		}
		List<Todo> list = c.list();
		return list;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	@Override
	public boolean update(Todo todo) {
		Session sesh = session();
		if (todo.getId() > 0) {
			Todo dbTodo = (Todo) sesh.get(Todo.class, todo.getId());
			if (dbTodo == null) {
				throw new NullPointerException("Todo with id "+todo.getId()+" not found");
			}
			// Avoid having to keep track of deletes and worry about ordering constraint
			for (TodoItem it : dbTodo.getItems()) {
				sesh.delete(it);
			}
			int i=1;
			for (TodoItem item : todo.getItems()) {
				item.setTodo(todo);
				item.setOrdering(i);
				sesh.save(item);
				i++;
			}
			dbTodo.setName(todo.getName());
			sesh.update(dbTodo);
		} else {
			throw new IllegalArgumentException("Todo missing id");
		}
		return false;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	@Override
	public long create(Todo todo) {
		Session sesh = session();
		if (todo.getAuthor() != null) {
			Author auth = (Author) sesh.load(Author.class, todo.getAuthor().getId());
			todo.setAuthor(auth);
		}
		Serializable id = sesh.save(todo);
		if (id != null) {
			int i=0;
			for (TodoItem item : todo.getItems()) {
				item.setTodo(todo);
				item.setOrdering(++i);
				sesh.save(item);
			}
		}
		return (Long) id;
	}
}
