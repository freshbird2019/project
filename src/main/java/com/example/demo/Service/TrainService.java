package com.example.demo.Service;

import com.example.demo.Dao.TrainRepository;
import com.example.demo.Dao.TrainpicRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.Domain.Trainpic;
import com.example.demo.Domain.User;
import com.example.demo.Domain.UserTrainpic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainpicRepository trainpicRepository;

    /*
    增加一条训练记录
     */
    @Transactional
    public UserTrainpic addATrain(int userid,int trainpicid,double a){
        System.out.println(a+"okkkkkkkkkkk");
        User user=userRepository.findById(userid).get();
        Trainpic trainpic=trainpicRepository.findById(trainpicid).get();
        Timestamp time=new Timestamp(System.currentTimeMillis());
        UserTrainpic userTrainpic=new UserTrainpic();
        userTrainpic.setUser(user);
        userTrainpic.setTrainpic(trainpic);
        userTrainpic.setAccuracy(a);
        userTrainpic.setTraintime(time);
        userTrainpic.setUsersign("abcd");
        return trainRepository.save(userTrainpic);
    }

    /*
    获取自己的训练记录
     */
    public List<UserTrainpic> findMyTrain(Integer id){
        return trainRepository.findtrain(id);
    }

    /*
    删除
     */
    public void DeleteATrain(Integer id){
        trainRepository.deleteById(id);
    }

    /*
    日期
     */
    public List<String> findTrainDate(Integer id){return trainRepository.findtraindate(id);}
}
