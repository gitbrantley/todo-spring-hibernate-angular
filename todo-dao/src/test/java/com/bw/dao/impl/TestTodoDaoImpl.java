package com.bw.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bw.dao.TodoDao;
import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Todo;
import com.bw.hibernate.entity.TodoItem;

public class TestTodoDaoImpl extends AbstractSpringBasedTest {

	@Autowired
	private TodoDao dao;
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void testCreateTodo() {
		Todo t = new Todo();
		t.setName("First Todo List");
		TodoItem i = new TodoItem();
		i.setValue("First todo item!");
		Set<TodoItem> items = new HashSet<TodoItem>();
		items.add(i);
		t.setItems(items);
		assertTrue("Did not get back a valid rid for Todo", dao.create(t) > 0);
		println("Created Todo with id "+t.getId());
	}
	
	@Test 
	public void testListTodos() {
		List<Todo> list = dao.list(new TodoCriteria());
		assertTrue("Did not get back any Todo lists", list.size() > 0);
		println("Got back "+list.size());
		for (Todo t : list) {
			println(t.getId() + " " + t.getName());
			Set<TodoItem> items = t.getItems();
			println("\tHas "+items.size());
			for (TodoItem i : items) {
				println("    "+i.getValue());
			}
		}
	}
	
}
