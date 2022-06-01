package com.example.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthRequest {
    private String userName;
    private String password;
}
