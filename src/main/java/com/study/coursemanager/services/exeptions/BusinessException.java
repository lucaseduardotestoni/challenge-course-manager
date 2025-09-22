package com.study.coursemanager.services.exeptions;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
    public BusinessException(String message) {
        super(message);
    }
}
