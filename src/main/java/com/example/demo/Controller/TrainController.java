package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserTrainpic;
import com.example.demo.Service.TrainService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class TrainController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;
    /*
    获取自己的训练记录
     */
    @GetMapping(value = "/GetMyTrain/{name}")
    public List<UserTrainpic> getMyTrain(@PathVariable("name") String name){
        User user=userService.findUserByName(name);

        return trainService.findMyTrain(user.getUserid());
    }


    /*
    增加一条训练记录
     */
    @GetMapping(value = "/UserTrain")
    public UserTrainpic userTrain(){
        return trainService.addATrain();
    }

    /*
    删除自己的训练记录
     */
    @GetMapping(value = "/deleteTrain/{id}")
    public void DeleteTrain(@PathVariable Integer id){
        trainService.DeleteATrain(id);
    }
}
