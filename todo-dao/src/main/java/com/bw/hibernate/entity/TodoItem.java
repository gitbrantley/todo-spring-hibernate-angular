package com.bw.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="todo_item")
public class TodoItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="todo_item_id")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "todo_id", nullable = false)
	@JsonIgnore
	private Todo todo;
	
	@Column(name="ordering")
	private int ordering=-1;
	
	@Column(name="value")
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long todoItemId) {
		this.id = todoItemId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
}
