package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Class used for login purpose.
 * An Object of type UserDetails is needed to perform authentication.
 * The object User of this project doesn't have all the fields needed for the login process to perform,
 * so this class is used to manage the login process.
 * The link with the object User of our database is made in UserDetailsServiceImpl.
 * Some values are set directly here:
 * - isAccountNonExpired
 * - isAccountNonLocked
 * - isCredentialsNonExpired
 * - isEnabled
 */
public class LoginRequest implements UserDetails {
    @JsonIgnore
    private String username;
    private String login;
    private String password;


    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getUsername() {
        return login;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}