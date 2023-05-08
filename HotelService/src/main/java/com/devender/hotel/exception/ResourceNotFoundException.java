package com.devender.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String entity,String id) {
        super(entity +" not found with id " + id);
    }
}
