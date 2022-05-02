package com.example.blog.security;

import com.example.blog.payloads.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Setter
@Getter
public class JwtUserDetails implements UserDetails {
    private Integer userId;

    private String name;

    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Integer userId, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }
    public static JwtUserDetails create(UserDto userDto){
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(userDto.getId(),userDto.getName(), userDto.getPassword(), authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true
                ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
