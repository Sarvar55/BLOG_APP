package com.example.blog.services.impl;

import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.UserDto;
import com.example.blog.payloads.UserRegisterRequest;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        User updatedUser = this.userRepo.save(user);
        UserDto userConvertDto = this.userToDto(updatedUser);

        return userConvertDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        // asagidaki islemimle ayni
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
    public UserRegisterRequest getByUserEmail(String email){
        User user = this.userRepo.findByEmail(email);
        return new UserRegisterRequest(user.getName(),user.getEmail(),user.getPassword());
    }
}
