package com.example.demo.Service;

import com.example.demo.Dao.MedicalpicRepository;
import com.example.demo.Domain.Medicalpic;
import com.example.demo.Domain.Trainpic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalpicService {
    @Autowired
    private MedicalpicRepository medicalpicRepository;
    private TrainpicService trainpicService;

    /*
    上传一张医学图像,或者更新
     */
    public Medicalpic addOrUpdateApic(Medicalpic medicalpic){
        return medicalpicRepository.save(medicalpic);
    }

    /*
    删除上传的医学图像
     */
    public void deleteAPicByid(Integer id){
        medicalpicRepository.deleteById(id);
    }

    /*
    将图像用于训练用途
     */
    public void MedicalToTrain(Medicalpic medicalpic){
        medicalpic.setFlag(1);
        trainpicService.addAPic(medicalpic.getSize(),medicalpic.getLocal());
    }

    /*
    根据id找图片
     */
    public Optional<Medicalpic> findMedicalById(Integer id){
        return medicalpicRepository.findById(id);
    }
}
