package com.sillysally.kyst02.dataObjects;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserModelDTO {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserModelDTO(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
