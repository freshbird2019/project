package com.example.demo.Dao;

import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    User save(User user);

    //@Query(nativeQuery =true,value = "select * from user where username=:name")
    User findByUsername(String name);

    User findByUsernameAndPwd(String username,String pwd);

    User findByUserid(int id);
}
