package com.test.todo.dao;

import java.util.List;

import com.test.todo.model.TodoEntity;

public interface TodoDao {

	public List<TodoEntity> getTodo();
	public void addTodo(TodoEntity todo);
	public void deleteTodo(int id);
}
