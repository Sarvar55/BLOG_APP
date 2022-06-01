package com.example.blog.payloads;

import lombok.*;


/**
 * @project: Blog
 * @author: Sarvar55
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
}
