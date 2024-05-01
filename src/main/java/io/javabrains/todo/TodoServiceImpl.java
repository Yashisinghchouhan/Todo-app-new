package io.javabrains.todo;

import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.javabrains.exception.TodoCollectionException;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepo;
	
	@Override
	public void createTodo(Todo todo) throws TodoCollectionException, SQLIntegrityConstraintViolationException {
		
		Optional<Todo> todoOptional = todoRepo.findByTodo(todo);
		if(todoOptional.isPresent()) {
			 throw new TodoCollectionException(TodoCollectionException.TodoAlreadyFoundException());
		}
		else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
		}
		
	}

	@Override
	public Todo retrieveSingleTodo(String id) throws TodoCollectionException,SQLIntegrityConstraintViolationException {
		Optional<Todo> todoOptional = todoRepo.findById(id);
		if(!todoOptional.isPresent()){
			throw new TodoCollectionException(TodoCollectionException.TodoNotFoundException(id));
		}else {
			return todoOptional.get();
		}
	}

	@Override
	public List<Todo> getAllTodos(){
		List<Todo> todosList = todoRepo.findAll();
		if(todosList.size()>0){
			return todosList;
		}
		else {
			return new ArrayList<Todo>();
		}
	}
	@Override
	public ResponseEntity<Todo> updateTodo(Todo todo, String id) {
		Optional<Todo> output = todoRepo.findById(id);
		if(output.isPresent())
		{
			Todo todoToSave = output.get();
			todoToSave.setDescription(todo.getDescription());
			todoToSave.setCompleted(todo.getCompleted());
			todoToSave.setCreatedAt(todo.getCreatedAt());
			todoToSave.setUpdatedAt(todo.getUpdatedAt());
			todoToSave.setTodo(todo.getTodo());

			todoRepo.save(todoToSave);
			return  new ResponseEntity<Todo>(todoToSave, HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}

	}

	@Override
	public void deleteTodoById(String id) throws TodoCollectionException{
		Optional<Todo> todoOptional = todoRepo.findById(id);
		if (!todoOptional.isPresent())
		{
			throw new TodoCollectionException(TodoCollectionException.TodoNotFoundException(id));
		}
		else
		 todoRepo.deleteById(id);

	}

}
