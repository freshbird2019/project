package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserService userService;

    /*
    注册时判断是否有相同用户名
     */
    @GetMapping(value = "/SameUsername/{name}")
    public Boolean userList(@PathVariable("name")String name){
        User user= userService.findUserByName(name);
        if(user==null)return false;
        return true;
    }

    /*
    注册
     */
    @PostMapping(value = "/UserRegister")
    public User userRegister( @RequestBody User user){
        return userService.userRegister(user);
    }

    /*
    登录,返回值为1是用户名不存在，2为密码错误
     */
    @GetMapping(value = "/UserLogin")
    public int userLogin( @RequestParam("name") String username,
                              @RequestParam("pwd") String password){
        return userService.userLogin(username,password);
    }

    /*
    根据用户名获取用户id
     */
    @GetMapping(value = "/getUseridByname/{name}")
    public int getUserIdByname(@PathVariable("name") String username){
        User user=userService.findUserByName(username);
        return user.getUserid();
    }

    /*
    修改用户信息
     */
    @PutMapping(value = "/updateUserInfo/{id}")
    public User updateUser(@PathVariable("id") Integer id,
                           @RequestParam("name") String username,
                           @RequestParam("pwd") String password,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           @RequestParam("sex") String sex,
                           @RequestParam("address") String address,
                           @RequestParam("hospital") String hospital){
        User user=new User();
        user.setUserid(id);
        user.setUsername(username);
        user.setPwd(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setSex(sex);
        user.setHospital(hospital);
        return userService.userUpdate(user);
    }

    /*
    根据name获取user
     */
    @GetMapping(value = "/UserByname/{name}")
    public User findUserByname(@PathVariable("name")String name){
        User user= userService.findUserByName(name);
        return user;
    }
}
