package com.bw.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="todo")
public class Todo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="todo_id")
	private long id;
	@Column(name="todo_name")
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "todo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy("ordering asc")
	private Set<TodoItem> items;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<TodoItem> getItems() {
		return items;
	}
	public void setItems(Set<TodoItem> items) {
		this.items = items;
	}

}
