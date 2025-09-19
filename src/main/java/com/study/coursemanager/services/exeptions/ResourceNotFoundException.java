package com.study.coursemanager.services.exeptions;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}