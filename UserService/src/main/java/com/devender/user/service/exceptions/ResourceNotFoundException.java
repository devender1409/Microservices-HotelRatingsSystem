package com.devender.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource Not Found on Server");
    }

    public ResourceNotFoundException(String entityName,String entityId) {
        super(entityName +" not found with id " + entityId);
    }
}
