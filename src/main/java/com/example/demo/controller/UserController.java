package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.LogoutRequestDTO;
import com.example.demo.dto.SignUpRequestDTO;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.StringConcatException;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User signup(@RequestBody SignUpRequestDTO signUpRequestDTO) throws UserAlreadyExistsException {
        String name = signUpRequestDTO.getName();
        String email = signUpRequestDTO.getEmail();
        String password = signUpRequestDTO.getPassword();
        return userService.signup(name, email, password);
    }

    @PostMapping("/login") //creating new token i.e. why its postMapping and update the db
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        return null;
    }

    @PatchMapping("/logout") // we are changing the isDeleted property but not adding anything to db
    public ResponseEntity<Void> logout(LogoutRequestDTO logoutRequestDTO){
        return null;
    }
}
