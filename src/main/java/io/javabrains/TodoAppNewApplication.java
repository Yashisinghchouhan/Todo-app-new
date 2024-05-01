package io.javabrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.javabrains.todo.TodoController;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = TodoController.class)
public class TodoAppNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppNewApplication.class, args);
	}

}
