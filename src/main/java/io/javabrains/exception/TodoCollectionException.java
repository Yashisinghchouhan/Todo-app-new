package io.javabrains.exception;

public class TodoCollectionException extends Exception{
	
	public static final long serialVersionUID = 1L;
	
	public TodoCollectionException(String msg) {
		 super(msg);
	}
	
	public static String TodoNotFoundException(String id) {
		
			return "id isn't found"+id;
		
	}
	
	public static String TodoAlreadyFoundException() {
		
		return "already found";
	}
}
