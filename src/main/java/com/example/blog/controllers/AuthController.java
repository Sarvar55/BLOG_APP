package com.example.blog.controllers;

import com.example.blog.exceptions.ApiException;
import com.example.blog.payloads.JwtAuthRequest;
import com.example.blog.payloads.JwtAuthResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.security.JwtTokenHelper;
import com.example.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenHelper tokenHelper;

    private final UserDetailsService detailsService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthController(JwtTokenHelper tokenHelper, UserDetailsService detailsService, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenHelper = tokenHelper;
        this.detailsService = detailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        this.authenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = this.detailsService.loadUserByUsername(request.getUserName());

        String token = this.tokenHelper.generateToken(userDetails);

        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(authResponse, HttpStatus.OK);
    }


    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details");
            throw new ApiException("Invalid username or password");
        }
    }

    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

}
