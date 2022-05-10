package com.example.blog.repositories;

import com.example.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
