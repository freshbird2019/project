package com.example.demo.Controller;

import com.example.demo.Dao.TrainpicRepository;
import com.example.demo.Domain.Trainpic;
import com.example.demo.Service.TrainpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class TrainpicController {
    @Autowired
    private TrainpicService trainpicService;
    /*
   查询训练过的图片
    */
    @GetMapping(value="/trainedpic/{id}")
    public List<Trainpic> TrainedList(@PathVariable("id") Integer id){
        return trainpicService.findTrainedpicById(id);
    }

    /*
   查询未训练过的图片
    */
    @GetMapping(value="/notrainedpic/{id}")
    public List<Trainpic> NoTrainedList(@PathVariable("id") Integer id){
        return trainpicService.findNoTrainedpicById(id);
    }

    /*
    随机抽取一条训练图片
     */
    @GetMapping(value = "/OneNoTrained/{id}")
    public Trainpic OneNoTrained(@PathVariable("id") Integer id){
        return trainpicService.findOne(id);
    }

    /*
    获取所有图片数量
     */
    @GetMapping(value = "/NumofAllTrainpic")
    public long NumofAllTrain(){
        return trainpicService.CountNum();
    }

    /*
    获取训练过的图像数量
     */
    @GetMapping(value = "/NumofTrained/{id}")
    public int NumofTrained(@PathVariable("id") Integer id){
        return trainpicService.findNumofTrained(id);
    }

    /*
    获取未训练过的图像数量
     */
    @GetMapping(value = "/NumofNoTrained/{id}")
    public int NumofNoTrained(@PathVariable("id") Integer id){
        return trainpicService.findNumofNoTrained(id);
    }

    /*
    插入一个图片
     */
    @PutMapping(value = "/AddaTrainpic")
    public Trainpic AddaTrainpic( @RequestParam("name") String local,
                                  @RequestParam("pwd") String size)
    {
        return trainpicService.addAPic(size,local);
    }

    /*
    删除一个图片
     */
    @GetMapping(value = "DelaTrainpic/{id}")
    public void DelaTrainpic(@PathVariable("id") Integer id){
        trainpicService.deleteAPicByid(id);
    }
}
