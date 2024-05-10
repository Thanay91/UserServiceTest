package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.exceptions.InvalidPasswordException;
import com.example.demo.exceptions.TokenInvalidOrExpiredException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.models.Token;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws InvalidPasswordException, UserDoesNotExistException {
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        Token token = userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        loginResponse.setToken(token.getValue());
        loginResponse.setMessage("Success");
        return loginResponse;
    }

    @PatchMapping("/logout") // we are changing the isDeleted property but not adding anything to db
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) throws TokenInvalidOrExpiredException {
        Token token = userService.loguot(logoutRequestDTO.getToken());
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(
                token.isDeleted()==true ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDTO isValidToken(@PathVariable(name = "tokenValue") String tokenValue){
        UserDTO userDTO = userService.validateToken(tokenValue);
        return userDTO;

    }
}
