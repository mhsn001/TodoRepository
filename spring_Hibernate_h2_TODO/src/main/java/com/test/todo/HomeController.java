package com.test.todo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.todo.model.TodoEntity;
import com.test.todo.service.TodoService;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Inject
	TodoService todoService;
		
	
	@RequestMapping(value ="/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, String> home() {
		Locale locale = new Locale("en", "");
		logger.info("Welcome home! The client locale is {}.", locale);
		

		//List<TodoEntity> todoList = todoService.getTodo();
			
		//logger.info("######## Todo list : {}", todoList);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		Map<String, String> m = new HashMap();
		m.put("serverTime", formattedDate);
		
		return m;
	}
	
	@RequestMapping(value = "/getTodo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TodoEntity> getToDo() {
		
		logger.info("Inside controller: getTodo() :: {}");
		List<TodoEntity> todoList = todoService.getTodo();
		
		for(TodoEntity t : todoList) {
			logger.info("Returning : addTodo() :: {} : {}",t.getTask(), t.getId());
		}
		return todoList;
	}
	
	/*@RequestMapping(value = "/addTodo", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<TodoEntity> addToDo(@RequestBody TodoEntity todo) {
		TodoEntity todo = new TodoEntity();
		todo.setCompleted(false);
		todo.setTask("Task to do ");
		logger.info("Inside controller: addTodo() :: {}", todo);
		List<TodoEntity> todoList = todoService.addTodo(todo);
		return todoList;
	}*/
	
	
	
	/*@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	public @ResponseBody List<TodoEntity> addToDo(@RequestParam("task") String task, @RequestParam("isCompleted") boolean isCompleted) {
		TodoEntity todoEntity = new TodoEntity();
		todoEntity.setCompleted(isCompleted);
		todoEntity.setTask(task);
		logger.info("Inside controller: addTodo() :: {}", task);
		todoService.addTodo(todoEntity);
		//logger.info("Returning : addTodo() :: {}",todoList);
		List<TodoEntity> todoList = todoService.getTodo();
		for(TodoEntity t : todoList) {
			logger.info("Returning : addTodo() :: {} : {}",t.getTask(), t.getId());
		}
		
		return todoList;
	}*/
	
	
	@RequestMapping(value = "/addTodo", method = RequestMethod.POST)
	public @ResponseBody List<TodoEntity> addToDo(@RequestBody TodoEntity todo) {
		/*TodoEntity todoEntity = new TodoEntity();
		todoEntity.setCompleted(todo.isCompleted());
		todoEntity.setTask(todo.getTask());*/
		logger.info("Inside controller: addTodo() :: {}", todo.getTask());
		todoService.addTodo(todo);
		//logger.info("Returning : addTodo() :: {}",todoList);
		List<TodoEntity> todoList = todoService.getTodo();
		for(TodoEntity t : todoList) {
			logger.info("Returning : addTodo() :: {} : {}",t.getTask(), t.getId());
		}
		
		return todoList;
	}
		
	@RequestMapping(value = "/deleteTodo1", method = RequestMethod.DELETE)
	public @ResponseBody List<TodoEntity> delete(@RequestParam("id") int id) {
		logger.info("Inside controller: deleteTodo() : {}", id);
	    todoService.deleteTodo(id);
	    List<TodoEntity> todoList = todoService.getTodo();
	    return todoList;
	}
	
	
			
}
