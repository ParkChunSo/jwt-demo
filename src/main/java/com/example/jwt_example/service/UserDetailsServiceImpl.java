package com.example.jwt_example.service;

import com.example.jwt_example.domain.MyUser;
import com.example.jwt_example.domain.MemberRole;
import com.example.jwt_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        MyUser user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound"));

        return new User(user.getId(), user.getPassword(), makeGrantedAuthority(user.getRoles()));

    }
    private Set<GrantedAuthority> makeGrantedAuthority(Set<MemberRole> roles) {
        return roles.stream()
                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                .collect(Collectors.toSet());
    }
}
