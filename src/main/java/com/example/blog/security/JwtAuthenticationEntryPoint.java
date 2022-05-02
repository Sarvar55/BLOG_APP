package com.example.blog.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        //burasi ise unAutorize istekler gelidigi zaman buraya yonlenecek
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());

    }
}
