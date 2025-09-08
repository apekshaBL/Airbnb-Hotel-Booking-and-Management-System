package com.AirBnbProject.AirBnbApplication.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }


}
