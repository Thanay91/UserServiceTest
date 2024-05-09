package com.example.demo.dto;

import com.example.demo.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token; //just value

    private String message;
}
