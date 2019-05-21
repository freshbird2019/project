package com.example.demo.Service;

import com.example.demo.Dao.UserRepository;
import com.example.demo.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //注册
    public User userRegister(User user) {
        System.out.println("ok");
        User user1=userRepository.save(user);
        userRepository.flush();
        System.out.println(user1.getUserid());
        return user1;
    }

    //更改用户信息
    public User userUpdate(User user) {
        return userRepository.save(user);
    }

    //根据id获取用户user
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    //根据name获取user
    public User findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    //根据id获取user
    public User findUserById(int id){return userRepository.findByUserid(id);}

    //登录
    public int userLogin(String name, String pwd) {
        int flag = 0;
        User user;
        System.out.println(name+"  "+pwd);
        user = findUserByName(name);
        if (user == null) flag = 0;//用户名不存在
        else {
            user = userRepository.findByUsernameAndPwd(name, pwd);
            if (user == null) flag = -1;//密码错误
            else flag=user.getUserid();
        }
        return flag;
    }
}
