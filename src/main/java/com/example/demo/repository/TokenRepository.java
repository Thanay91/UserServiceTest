package com.example.demo.repository;

import com.example.demo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Token save(Token token);

    public Optional<Token> findTokenByValueAndExpiryAtGreaterThanAndDeleted(String tokenValue, Date timeStamp, boolean isDeleted);
}
