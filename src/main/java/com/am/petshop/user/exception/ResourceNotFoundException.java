package com.am.petshop.user.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message){
		super(message);
	}
}
