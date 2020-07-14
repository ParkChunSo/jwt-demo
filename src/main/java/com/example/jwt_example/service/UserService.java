package com.example.jwt_example.service;

import com.example.jwt_example.config.JwtTokenProvider;
import com.example.jwt_example.domain.MyUser;
import com.example.jwt_example.domain.UserDto;
import com.example.jwt_example.domain.MemberRole;
import com.example.jwt_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String userId, String password){
        MyUser user = userRepository.findById(userId).orElseThrow();

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("PasswordNotMatch");

        return jwtTokenProvider.createToken(user.getId(), user.getRoles());
    }

    public void signUp(UserDto dto, MemberRole role){
        if(userRepository.existsById(dto.getId()))
            throw new RuntimeException("AlreadyExistUser");
        Set<MemberRole> roles =
                role == MemberRole.CLIENT ? Set.of(MemberRole.CLIENT) : Set.of(MemberRole.CLIENT, MemberRole.ADMIN);
        userRepository.save(new MyUser(dto.getId(),passwordEncoder.encode(dto.getPassword()), dto.getAddress(), roles));
    }

    public UserDto findById(String userId){
        return new UserDto(userRepository.findById(userId).orElseThrow());
    }

    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
