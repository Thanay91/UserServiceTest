package com.example.demo.models;

import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private boolean isEmailVerified;



    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.email);
        userDTO.setEmailVerified(user.isEmailVerified);
        userDTO.setRoles(user.roles);
        userDTO.setName(user.name);
        return userDTO;
    }
}
