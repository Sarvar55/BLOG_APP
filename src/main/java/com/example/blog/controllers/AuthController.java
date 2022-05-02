package com.example.blog.controllers;

import com.example.blog.payloads.UserDto;
import com.example.blog.payloads.UserLoginRequest;
import com.example.blog.payloads.UserRegisterRequest;
import com.example.blog.security.JwtTokenProvider;
import com.example.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);//bize bir autentication objesi donuyor

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        if (userService.getByUserEmail(request.getUsername()) != null) {
            return new ResponseEntity<String>("UserName already use.", HttpStatus.BAD_REQUEST);
        }

        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername(request.getUsername());
        registerRequest.setEmail(request.getEmail());
        registerRequest.setPassword(passwordEncoder.encode(request.getPassword()));
        return new ResponseEntity<String>("User Successfully registered",HttpStatus.CREATED);
    }

}
