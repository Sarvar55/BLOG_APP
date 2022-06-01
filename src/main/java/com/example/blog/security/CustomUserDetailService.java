package com.example.blog.security;

import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo repo;

    public CustomUserDetailService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = repo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email:" + username, 0));
        return user;
    }
}
