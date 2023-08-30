package com.dzhanrafetov.melifera.security;

import com.dzhanrafetov.melifera.enumeration.Role;
import com.dzhanrafetov.melifera.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserDetails implements UserDetails {
    private String username;
    private String password; // Add a password field
    private List<GrantedAuthority> authorities;

    public JwtUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword(); // Set the password here
        this.authorities = Arrays.stream(user.getRole().name().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password; // Return the stored password
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; // You can add your own logic here to check user status
    }
}
