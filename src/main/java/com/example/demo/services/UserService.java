package com.example.demo.services;

import com.example.demo.exceptions.InvalidPasswordException;
import com.example.demo.exceptions.TokenInvalidOrExpiredException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.models.Token;
import com.example.demo.models.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    public User signup(String name, String email, String password) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists please go to Login page");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User savedUser =  userRepository.save(user);
        return savedUser;
    }

    public Token login(String email, String password) throws UserDoesNotExistException, InvalidPasswordException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserDoesNotExistException("User not found please register first");
        }
        User savedUser = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, savedUser.getHashedPassword())){
            throw new InvalidPasswordException("Password is wrong");
        }

        //setting user
        Token token = new Token();
        token.setUser(savedUser);

        //setting expiry
        Calendar calendar= Calendar.getInstance();
        calendar.add(calendar.DATE, 30);
        Date expityDate = calendar.getTime();
        token.setExpiryAt(expityDate);

        //setting token value(JWT token)
        //for now random alphanumeric of 120 chars

        String tokenValue = RandomStringUtils.randomAlphanumeric(120);
        token.setValue(tokenValue);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    public Token loguot(String tokenValue) throws TokenInvalidOrExpiredException {
        Optional<Token> optionalToken = tokenRepository.findTokenByValueAndExpiryAtGreaterThanAndDeleted(tokenValue, new Date(), false);
        if(optionalToken.isEmpty()){
            throw new TokenInvalidOrExpiredException("Token is invalid or Expired");
        }
        Token existingToken = optionalToken.get();
        existingToken.setDeleted(true);
        Token savedToken = tokenRepository.save(existingToken);
        return savedToken;
    }

    public Token validateToken(String tokenValue){
        Optional<Token> optionalToken = tokenRepository.findTokenByValueAndExpiryAtGreaterThanAndDeleted(
                tokenValue, new Date(), false);
        if(optionalToken.isEmpty()){
            return null;
        }
        return optionalToken.get();
    }
}
