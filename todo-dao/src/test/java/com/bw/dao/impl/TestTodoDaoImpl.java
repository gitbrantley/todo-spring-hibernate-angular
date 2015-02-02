package com.bw.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bw.dao.TodoDao;
import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Author;
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
		Author a = new Author();
		a.setId(1);
		t.setAuthor(a);
		t.setName("First Todo List");
		TodoItem i = new TodoItem();
		i.setValue("First todo item!");
		i.setOrdering(1);
		t.addItem(i);
		i = new TodoItem();
		i.setValue("Second todo item!");
		i.setOrdering(2);
		t.addItem(i);
		assertTrue("Did not get back a valid rid for Todo", dao.create(t) > 0);
		println("Created Todo with id "+t.getId());
	}
	
	@Test 
	public void testListTodos() {
		List<Todo> list = dao.list(new TodoCriteria());
		assertTrue("Did not get back any Todo lists", list.size() > 0);
		println("Got back "+list.size());
		for (Todo t : list) {
			println(t.getId() + " " + t.getName()+" by "+(t.getAuthor() != null ? t.getAuthor().getUsername() : "[none]"));
			Set<TodoItem> items = t.getItems();
			println("\tHas "+items.size());
			for (TodoItem i : items) {
				println("    "+i.getValue());
			}
		}
	}
	
}
