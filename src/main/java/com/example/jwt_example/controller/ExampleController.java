package com.example.jwt_example.controller;

import com.example.jwt_example.domain.UserDto;
import com.example.jwt_example.domain.MemberRole;
import com.example.jwt_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ExampleController {
    private final UserService userService;

    /**
     * 모두 접근 가능
     * @param dto 유저 정보
     */
    @PostMapping("/login")
    public String login(@RequestBody UserDto dto){
        return userService.login(dto.getId(), dto.getPassword());
    }

    /**
     * 모두 접근 가능
     * @param dto 유저 정보
     */
    @PostMapping("/client/signUp")
    public void signUpClient(@RequestBody UserDto dto){
        userService.signUp(dto, MemberRole.CLIENT);
    }

    /**
     * 관리자만 접근 가능
     * @param dto 유저 정보
     */
    @PostMapping("/admin/signUp")
    public void signUpAdmin(@RequestBody UserDto dto){
        userService.signUp(dto, MemberRole.ADMIN);
    }

    /**
     * 관리자와 자기 자신만 접근 가능
     * @param userId 유저 ID
     * @return 특정 유저 정보
     */
    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable String userId){
        return userService.findById(userId);
    }

    /**
     * 관리자만 접근 가능
     * @return 모든 유저 리스트
     */
    @GetMapping("/all")
    public List<UserDto> findAll(){
        return userService.findAll();
    }
}
