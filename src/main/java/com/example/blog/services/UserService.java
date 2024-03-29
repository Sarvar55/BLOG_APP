package com.example.blog.services;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.payloads.UserRegisterRequest;

import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface UserService {

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
