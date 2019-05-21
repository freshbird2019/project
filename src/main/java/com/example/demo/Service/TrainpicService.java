package com.example.demo.Service;

import com.example.demo.Dao.TrainpicRepository;
import com.example.demo.Domain.Trainpic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainpicService {
    @Autowired
    private TrainpicRepository trainpicRepository;

    //获取所有训练图片
    List<Trainpic> findAllTpic(){
        return trainpicRepository.findAll();
    }

    //获取所有图像数量
    public long CountNum(){
        return trainpicRepository.count();
    }

    //找到用户训练过的图像
    public List<Trainpic> findTrainedpicById(Integer id){
        return trainpicRepository.findtrainedpic(id);
    }

    //获取用户训练图像数量
    public int findNumofTrained(Integer id){
        List<Trainpic> trainpics=findTrainedpicById(id);
        return trainpics.size();
    }

    //找到用户未训练图像
    public List<Trainpic> findNoTrainedpicById(Integer id){
        return trainpicRepository.findnotrainedpic(id);
    }

    //获取用户未训练图像数量
    public int findNumofNoTrained(Integer id){
        List<Trainpic> trainpics=findNoTrainedpicById(id);
        return trainpics.size();
    }

    //插入一个训练图片
    public Trainpic addAPic(String size,String local){
        Trainpic trainpic=new Trainpic();
        trainpic.setSize(size);
        trainpic.setLocal(local);
        return trainpicRepository.save(trainpic);
    }

    //删除一个训练图片
    public void deleteAPicByid(Integer id){
        trainpicRepository.deleteById(id);
    }

    //随机抽取一个训练图像
    public Trainpic findOne(Integer id){
        return trainpicRepository.findRandnotrainedpic(id);
    }

    //根据图片id获取图片
    public Optional<Trainpic> findById(Integer id){
        return trainpicRepository.findById(id);
    }
}
