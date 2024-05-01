package io.javabrains.todo;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.exception.TodoCollectionException;

@RestController
public class TodoController {
	
	@Autowired
	TodoRepository todoRepo;
	
	@Autowired
	TodoService todoService;

	@GetMapping("/todos/all")
	public List<Todo> getAllTodos() {
		return todoService.getAllTodos();
	}
	
 
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getTodoWithId(@PathVariable("id") String id){
		try {
			return  new ResponseEntity<Todo>(todoService.retrieveSingleTodo(id), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}

	
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?> postAllTodos(@RequestBody Todo todo) {
			
		try {
			todoService.createTodo(todo);
			return new ResponseEntity<Todo>(todo,HttpStatus.OK);
		}
		catch ( SQLIntegrityConstraintViolationException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		//return todoRepo.findAll();
 catch (TodoCollectionException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
		
	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodoById(@PathVariable("id") String id, @RequestBody Todo todo){
		ResponseEntity<Todo> responseEntity=todoService.updateTodo(todo, id);
		return responseEntity;
	}  
	
	@DeleteMapping("/todos/delete/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id) {
		try {
			todoService.deleteTodoById(id);
			return new ResponseEntity<>("Deleted successfully!",HttpStatus.OK);

		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	
}
