package com.github.jumagaliev1.backendAssignment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String key, Long id) {
        super(resource + " not found " + key + ":" + id);
    }
}
