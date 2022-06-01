package com.example.blog.repositories;

import com.example.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

}
