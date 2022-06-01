package com.example.blog.controllers;

import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @project: Blog
 * @author: Sarvar55
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);//status  code 201 created
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
                                              @PathVariable Integer userId) {
        UserDto updateUserDto = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updateUserDto, HttpStatus.OK);//status code 200
    }

//    //@DeleteMapping
//    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
//        this.userService.deleteUser(userId);
//        return new ResponseEntity(Map.of("message", "User deleted Succesfully"), HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")
                                                          Integer userId) {
        this.userService.deleteUser(userId);//ResourceNotFoundException
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Succesfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);//Stats code 200 success
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);
    }

}
