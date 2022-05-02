package com.example.blog.services.impl;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.findByUserName(username);

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return JwtUserDetails.create(userDto);
    }

    public UserDetails loadUserById(Integer userId) {
        User user = this.userRepo.findById(userId).get();
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return JwtUserDetails.create(userDto);
    }
}
