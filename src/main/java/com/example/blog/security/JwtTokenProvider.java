package com.example.blog.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Component
public class JwtTokenProvider  {
    @Value("${BlogApp.secret.key}")
    private String SECRET_KEY;
    @Value("${BlogApp.expires}")
    private long EXPIRES_IN;

    public String generateJwtToken(Authentication auth){//token olusturma
        JwtUserDetails jwtUserDetails=(JwtUserDetails) auth.getPrincipal();//bu bizim Autentice edicegimiz olan user
        Date expiresDate=new Date (new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().setSubject(Integer.toString(jwtUserDetails.getUserId()))
                .setIssuedAt(new Date()).setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    public Integer getUserFromToken(String token){
        return Integer.parseInt(this.tokenParser(token).getSubject());
    }
    public Claims tokenParser(String token) {//aslinda bu bir Obje olarak gelir yani Json veris gibi parse etib ayiriyirouz (getBody dedigimiz yer ise setSubject setIssueAt gigi alanlari icinde sakliyor)
       return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();//bu tokeni verib yeniden geri cozmemizi sagliyor
    }
    public boolean validateToken(String token){//burda ise bakiyoruz gonderrilen token dogrumu ve ya suresi gecmiz mi

        try{
            this.tokenParser(token);//eger bu parse oluna biliniyorsa o zaman bizim uygulamamiz tarafindan olustruulmusdur ve dogrudur
            return  !isTokenExpired(token);
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){//10 * 60 * 360 ==51 saat gibi cikti
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        Date expretion=this.tokenParser(token).getExpiration();
        return expretion.before(new Date());
    }

}
