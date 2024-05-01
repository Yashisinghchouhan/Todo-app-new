package io.javabrains.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.javabrains.exception.TodoCollectionException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public interface TodoService{

	public void createTodo(Todo todo) throws TodoCollectionException, SQLIntegrityConstraintViolationException;
	public Todo  retrieveSingleTodo(String id) throws TodoCollectionException, SQLIntegrityConstraintViolationException;
	public ResponseEntity<Todo> updateTodo(Todo todo, String id);

	public List<Todo> getAllTodos();
	public void deleteTodoById(String id) throws TodoCollectionException;
}
