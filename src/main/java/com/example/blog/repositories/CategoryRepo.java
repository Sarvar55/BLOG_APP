package com.example.blog.repositories;

import com.example.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
