package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;
    private Date expiryAt;

    //T  :  U
    //1  :  1
    //M  :  1
    @ManyToOne
    private User user;
}
