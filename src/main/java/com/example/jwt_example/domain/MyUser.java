package com.example.jwt_example.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    private String id;
    private String password;
    private String address;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<MemberRole> roles;

    public MyUser(UserDto dto, Set<MemberRole> roles){
        this.id = dto.getId();
        this.password = dto.getPassword();
        this.address = dto.getAddress();
        this.roles = roles;
    }
}
