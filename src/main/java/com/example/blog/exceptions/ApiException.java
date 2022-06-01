package com.example.blog.exceptions;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super();
    }
}
