package com.example.blog.services.impl;

import com.example.blog.entities.Comment;
import com.example.blog.entities.Post;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repositories.CommentRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=postRepo.
                findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        Comment comment= this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.
                findById(commentId).
                orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        this.commentRepo.delete(comment);
    }
}
