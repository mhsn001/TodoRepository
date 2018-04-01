package com.test.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Todo")
public class TodoEntity {
	@Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
	
	@Column(name="task")
	String task;
	
	@Column(name="Completed")
	boolean completed;
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean isCompleted) {
		this.completed = isCompleted;
	}
		
}
