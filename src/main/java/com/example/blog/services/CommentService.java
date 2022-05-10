package com.example.blog.services;

import com.example.blog.payloads.CommentDto;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);


}
