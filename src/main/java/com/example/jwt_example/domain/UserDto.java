package com.example.jwt_example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String id;
    private String password;
    private String address;

    public UserDto(MyUser entity){
        this.id = entity.getId();
        this.password = entity.getPassword();
        this.address = entity.getAddress();
    }
}
