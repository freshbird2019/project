package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class DemoController {
    @RequestMapping("/hello")
    private String index(){
        return "hello world!";
    }

    private String name;
    @RequestMapping(value = "/showname",method = RequestMethod.GET)
    public String a(){
        return "hello, "+name;
    }
   @Autowired
    private UserRepository userRepository;
    /*
    查询所有用户列表
     */
    @GetMapping(value="/users")
    public List<User> userList(){
        return userRepository.findAll();
    }
    /*
    根据id查找指定User
     */
    @GetMapping(value = "/userid/{id}")
    public Optional<User> findUserById(@PathVariable("id") Integer id){
        return userRepository.findById(id);
    }
    /*
    添加一个User
     */
    @PostMapping(value = "/addUser")
    public User addUser(
            @RequestParam("name") String username,
            @RequestParam("pwd") String password
    ){
        User user=new User();
        user.setUsername(username);
        user.setPwd(password);
        return userRepository.save(user);
    }
    /*
    更新User
     */
    @PutMapping(value = "updateUser/{id}")
    public User updateUser(
            @PathVariable("id") Integer id,
            @RequestParam("name") String username,
            @RequestParam("pwd") String password
    ){
        User user=new User();
        user.setUserid(id);
        user.setUsername(username);
        user.setPwd(password);
        return userRepository.save(user);
    }
    /*
    删除user
     */
    @GetMapping(value = "del/{id}")
    public void delUserById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }
    /*
    根据username查询
     */
    @GetMapping(value = "/username/{name}")
    public List<User> userList(@PathVariable("name")String name){
        return userRepository.findByUsername(name);
    }
}

