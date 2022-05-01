package com.example.blog.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)//status code 404
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String filedName;
    private long fieldValue;

    public ResourceNotFoundException(String resurceName, String filedName, long fieldValue) {
        super(String.format("%s not found with %s : %d", resurceName, filedName, fieldValue));
        this.resourceName = resurceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
