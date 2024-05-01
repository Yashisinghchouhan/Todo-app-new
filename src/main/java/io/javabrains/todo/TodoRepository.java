package io.javabrains.todo;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {
	@Query("{'todo':?0}")
	Optional<Todo> findByTodo(Todo todo);

}
