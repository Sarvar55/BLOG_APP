package com.example.blog.repositories;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("Select post from Post post where post.title like %:title%")
    List<Post> findByTitleContaining(@Param("title")String title);



}
