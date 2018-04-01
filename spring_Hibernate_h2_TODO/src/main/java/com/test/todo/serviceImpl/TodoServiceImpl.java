package com.test.todo.serviceImpl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.todo.HomeController;
import com.test.todo.dao.TodoDao;
import com.test.todo.model.TodoEntity;
import com.test.todo.service.TodoService;
@Service
@Transactional
public class TodoServiceImpl implements TodoService{
	
	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	@Inject
	TodoDao todoDao;

	@Override
	public List<TodoEntity> getTodo() {
		// TODO Auto-generated method stub
		return todoDao.getTodo();
	}

	@Override
	public void addTodo(TodoEntity todo) {
		// TODO Auto-generated method stub
		logger.info("inside TodoService.addTod() :: {} ", todo.getTask());
		todoDao.addTodo(todo);
	}

	@Override
	public void deleteTodo(int id) {
		logger.info("inside TodoService.deleteTodo()");
		todoDao.deleteTodo(id);
	}

}
