package com.study.coursemanager.services.exeptions;

import java.io.Serializable;

public class InvalidUserDataExeption extends RuntimeException implements Serializable {

    public InvalidUserDataExeption(String message) {
        super("Invalid user data: " + message);
    }
}
