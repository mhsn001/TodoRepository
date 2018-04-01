package com.test.todo.service;

import java.util.List;

import com.test.todo.model.TodoEntity;

public interface TodoService {

	public List<TodoEntity> getTodo();
	public void addTodo(TodoEntity todo);
	public void deleteTodo(int id);
	

}
