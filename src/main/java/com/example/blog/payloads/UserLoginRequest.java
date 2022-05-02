package com.example.blog.payloads;

import lombok.Data;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
