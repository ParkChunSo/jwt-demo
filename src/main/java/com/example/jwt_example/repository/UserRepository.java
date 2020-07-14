package com.example.jwt_example.repository;

import com.example.jwt_example.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<MyUser, String> {
}
