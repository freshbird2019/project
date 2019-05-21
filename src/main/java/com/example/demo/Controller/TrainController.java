package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserTrainpic;
import com.example.demo.Service.TrainService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @GetMapping(value = "/GetMyTrain/{id}")
    public List<UserTrainpic> getMyTrain(@PathVariable("id") Integer id){

        return trainService.findMyTrain(id);
    }

    /*
    获取自己的训练记录
     */
    @GetMapping(value = "/GetMyTrainDate/{id}")
    public List<String> getMyTrainDate(@PathVariable("id") Integer id){

        return trainService.findTrainDate(id);
    }


    /*
    增加一条训练记录
     */
    @RequestMapping(value = "/UserTrain")
    @ResponseBody
    public UserTrainpic userTrain(@RequestParam(value = "userid",required = false) int id1,
                                  @RequestParam(value = "trainpicid",required = false) int id2,
                                  @RequestParam(value = "accuracy",required = false) double a){
        return trainService.addATrain(id1,id2,a);
    }

    /*
    删除自己的训练记录
     */
    @GetMapping(value = "/deleteTrain/{id}")
    public void DeleteTrain(@PathVariable Integer id){
        trainService.DeleteATrain(id);
    }
}
