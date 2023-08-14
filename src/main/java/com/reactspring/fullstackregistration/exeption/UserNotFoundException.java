package com.reactspring.fullstackregistration.exeption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super("Could not found with id "+ id);
        
    }
    
}
