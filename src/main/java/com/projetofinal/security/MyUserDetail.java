package com.projetofinal.security;

import com.projetofinal.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetail implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean active;

    public MyUserDetail(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        authorities = new ArrayList<>();
        user.getAuthorityList().forEach(role -> authorities.add(role));
    }

    public MyUserDetail(String userName, String password, List<GrantedAuthority> authorities, boolean active) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
    }

    public MyUserDetail() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return active;
    }
}
