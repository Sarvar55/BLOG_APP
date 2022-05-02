package com.example.blog.security;

import com.example.blog.services.impl.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Component
@Data
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtTokenProvider jwtTokenProvider;

    private UserDetailServiceImpl detailsService;


    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailServiceImpl detailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.detailsService = detailsService;
    }

    @Override
//bu bir filter methodu frontendtden bir request gelidigi zaman    bu ruwuest Autorize olmus mu diye degilse 401 diye unAutorize
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                Integer userId = jwtTokenProvider.getUserFromToken(jwtToken);
                UserDetails user = detailsService.loadUserById(userId);
                if (user != null) {//varsa autentice edicegiz
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                            UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    //user icin gerekli security biligilerini tutan bir yer gibi
                }
            }
        } catch (Exception e) {
            return;
        }
        filterChain.doFilter(request, response);/*
        yeni genel olarak soyle oluyor 5-6 tane asama var spring bize sundugu biz burda bize gele isteklerin kimligini kontrol
        ediyoruz eger gelen istekler dogru ise spring yine o zingire devam ediyor ama burdan da gecmesi gerek catch yakalanarsa zaten durucakk
        */
    }

    private String extractJwtFromRequest(HttpServletRequest request) {//istek atarekn headerlerde Autorization basligi altinda gondericegiz ve yaninda da "bearer" tokeni olur
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {//eger boyle ise dogrudur gelimisdr
            return bearer.substring(bearer.lastIndexOf(" ")).trim();
        }
        return null;
    }
}
