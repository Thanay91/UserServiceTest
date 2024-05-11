package com.example.demo.security.models;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
//    private User user;

    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;

    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<CustomGrantedAuthority> authorities;


    private Long id;


      public CustomUserDetails(User user){
          this.username = user.getName();
          this.password = user.getHashedPassword();
          this.accountNonExpired = true;
          this.accountNonLocked = true;
          this.credentialsNonExpired = true;
          this.enabled = true;
          this.id = user.getId();
          List<CustomGrantedAuthority> authorities = new ArrayList<>();

          for(Role role:user.getRoles()){
              CustomGrantedAuthority authority = new  CustomGrantedAuthority(role);
              authorities.add(authority);
          }
          this.authorities = authorities;
      }
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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId(){
          return id;
    }
}
