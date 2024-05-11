package com.example.demo.security.services;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.models.CustomUserDetails;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {


    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with email does not exist, please register first");
        }
        User user = optionalUser.get();
        UserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
