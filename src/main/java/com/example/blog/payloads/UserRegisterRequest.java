package com.example.blog.payloads;

import lombok.*;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
}
