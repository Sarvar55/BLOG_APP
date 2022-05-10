package com.example.blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Getter
@Setter
public class CommentDto {
    private Integer commentId;
    private String content;
}
