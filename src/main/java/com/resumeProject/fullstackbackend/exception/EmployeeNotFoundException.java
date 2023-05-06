package com.resumeProject.fullstackbackend.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String username){
        super("Could not found the employee with username: " + username);
    }
}
