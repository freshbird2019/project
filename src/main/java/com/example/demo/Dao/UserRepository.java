package com.example.demo.Dao;

import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String name);

    User findByUsernameAndPwd(String username,String pwd);
}
