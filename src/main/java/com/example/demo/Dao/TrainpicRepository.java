package com.example.demo.Dao;

import com.example.demo.Domain.Trainpic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainpicRepository extends JpaRepository<Trainpic,Integer> {

    //找到该用户已训练后的图片
     @Query(nativeQuery =true,value = "select * from train,trainpic t,user where train.userid=user.userid " +
            "and train.trainpicid=t.trainpicid and train.userid=:id")
    List<Trainpic> findtrainedpic(Integer id);

    //找到该用户未训练的图片
    @Query(nativeQuery =true,value = "select * from trainpic where trainpicid not in " +
            "(select t.trainpicid from train,trainpic t,user where train.userid=user.userid " +
            "and train.trainpicid=t.trainpicid and train.userid=:id)")
    List<Trainpic> findnotrainedpic(Integer id);

    //随机选取一张未训练图片
    @Query(nativeQuery =true,value = "select * from trainpic where trainpicid not in " +
            "(select t.trainpicid from train,trainpic t,user where train.userid=user.userid " +
            "and train.trainpicid=t.trainpicid and train.userid=:id) order by rand() limit 1")
    Trainpic findRandnotrainedpic(Integer id);
}
