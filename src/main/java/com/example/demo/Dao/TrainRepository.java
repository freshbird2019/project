package com.example.demo.Dao;


import com.example.demo.Domain.UserTrainpic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<UserTrainpic,Integer> {
    //找到该用户的训练记录
    @Query(nativeQuery =true,value = "select * from train where userid=:id order by traintime desc")
    List<UserTrainpic> findtrain(Integer id);
}
