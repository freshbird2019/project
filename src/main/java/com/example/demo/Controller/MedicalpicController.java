package com.example.demo.Controller;

import com.example.demo.Domain.Medicalpic;
import com.example.demo.Domain.User;
import com.example.demo.Service.MedicalpicService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class MedicalpicController {
    @Autowired
    private MedicalpicService medicalpicService;
    private UserService userService;

    /*
    上传一张医学图像,state=0
    这边这个上传者是要前端设置好？还是传过来给后端设置？
     */
    @PostMapping(value = "/AddaMedicalpic/{name}")
    public Medicalpic AddMedical(@RequestBody Medicalpic medicalpic,
                                 @PathVariable(value = "name") String name){
        medicalpic.setState(0);
        User user=userService.findUserByName(name);
        medicalpic.setMedicalpicid(user.getUserid());
        return medicalpicService.addOrUpdateApic(medicalpic);
    }

    /*
    图片处理成功后，state=1
     */
    @PutMapping(value = "/SuccessMedicalpic")
    public Medicalpic UpdateMedical(@RequestBody Medicalpic medicalpic){
        medicalpic.setState(1);
        if(medicalpic.getFlag()==1){
            medicalpicService.MedicalToTrain(medicalpic);
        }
        return medicalpicService.addOrUpdateApic(medicalpic);
    }

    /*
    将图片提供给训练图像
     */
    public void MedicalToTrain(@PathVariable(value = "id")Integer id){
        //Medicalpic medicalpic=(Medicalpic)GetMpic(id);

    }

    /*
    根据id获取图片
     */
    @PutMapping(value = "/GetMedicalpicByid/{id}")
    public Optional<Medicalpic> GetMpic(@PathVariable(value = "id")Integer id){
        return medicalpicService.findMedicalById(id);
    }
    /*
    删除处理过的医学图片
     */
    @GetMapping(value = "/deleteMedicalpic/{id}")
    public void DeleteMedical(@PathVariable Integer id){
        medicalpicService.deleteAPicByid(id);
    }

}
