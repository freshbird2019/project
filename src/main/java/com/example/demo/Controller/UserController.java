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
    @ResponseBody
    @RequestMapping(value = "/UserRegister")
    public User Xyregister(@RequestBody User user) {
        System.out.println(user.getUsername());
        user.setUserid(0);
        return userService.userRegister(user);
    }

    /*
    登录,返回值为1是用户名不存在，2为密码错误
     */
    @RequestMapping(value = "/UserLogin")
    @ResponseBody
    public int userLogin( @RequestParam(value = "name",required = false) String username,
                              @RequestParam(value = "pw",required = false) String password){
        return userService.userLogin(username,password);
    }

    /*
    根据用户名获取用户id
     */
    @RequestMapping(value = "/getUseridByname/{name}")
    @ResponseBody
    public int getUserIdByname(@PathVariable("name") String username){

        User user=userService.findUserByName(username);
        System.out.println(user);
        return user.getUserid();
    }

    /*
    修改用户信息
     */
    @PutMapping(value = "/updateUserInfo/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") Integer id,
                           @RequestParam("name") String username,
                           @RequestParam("pwd") String password,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           @RequestParam(value = "sex",required = false) String sex,
                           @RequestParam(value = "address",required = false) String address,
                           @RequestParam(value = "hospital",required = false) String hospital,
                           @RequestParam("age")Integer age
                           ){
        User user=new User();
        user.setUserid(id);
        user.setUsername(username);
        user.setPwd(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setSex(sex);
        user.setHospital(hospital);
        user.setAge(age);
        return userService.userUpdate(user);
    }

    /*
    根据name获取user
     */
    @GetMapping(value = "/UserByname/{name}")
    @ResponseBody
    public User findUserByname(@PathVariable("name")String name){
        User user= userService.findUserByName(name);
        return user;
    }
}
