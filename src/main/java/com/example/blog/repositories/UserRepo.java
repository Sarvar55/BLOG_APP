package com.example.blog.repositories;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String username);

    User findByEmail(String email);
}
