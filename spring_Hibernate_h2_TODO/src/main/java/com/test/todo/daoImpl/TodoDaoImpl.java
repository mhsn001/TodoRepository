package com.test.todo.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.todo.dao.TodoDao;
import com.test.todo.model.TodoEntity;
import com.test.todo.service.TodoService;
import com.test.todo.serviceImpl.TodoServiceImpl;


@Repository
public class TodoDaoImpl implements TodoDao{
	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<TodoEntity> getTodo() {
		Session session = sessionFactory.openSession();
		List<TodoEntity> todoList = session.createQuery("from TodoEntity").list();
		session.close();
		return todoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTodo(TodoEntity todo) {
		// TODO Auto-generated method stub
		logger.info("inside TodoDao.addTod() :: {} ", todo.getTask());
		Session session = sessionFactory.openSession();
		session.save(todo);
		session.close();
		//return (List<TodoEntity>)session.createQuery("from TodoEntity").list();
	}

	@Override
	public void deleteTodo(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		
		/*org.hibernate.Query query = session.createQuery("from TodoEntity where id = ?");
	    query.setInteger(0, id);
		TodoEntity todo = (TodoEntity)query.uniqueResult();*/
		
		//TodoEntity todo = (TodoEntity)session.get(TodoEntity.class, id);
		
		logger.info("inside TodoDao.deleteTodo() :: {}  ", id);
		//session.delete(todo);
		
		Query q = session.createQuery("delete TodoEntity where id = ?");
		q.setInteger(0, id);
		int deleteStatus = q.executeUpdate();
		
		session.close();
		
	}

	
}
