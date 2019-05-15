package com.example.demo.Controller;

import com.example.demo.Domain.Medicalpic;
import com.example.demo.Domain.User;
import com.example.demo.Service.MedicalpicService;
import com.example.demo.Service.UserService;
import com.example.demo.Controller.FileUtil;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class MedicalpicController {
    @Autowired
    private MedicalpicService medicalpicService;
    @Autowired
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
        medicalpic.setUser(user);//设置上传者
        Timestamp time=new Timestamp(System.currentTimeMillis());
        medicalpic.setUploadtime(time);//设置上传时间
        return medicalpicService.addOrUpdateApic(medicalpic);
    }
    /*
    上传test
     */
 /*   @GetMapping(value = "/testAddaMedicalpic/{name}")
    public Medicalpic testAddMedical(@PathVariable(value = "name") String name){
        Medicalpic medicalpic=new Medicalpic();
        medicalpic.setDesc("hello");
        medicalpic.setSize("123");
        medicalpic.setState(0);
        medicalpic.setFlag(0);
        medicalpic.setLocal("abcdefg");
        User user=userService.findUserByName(name);
        medicalpic.setUser(user);//设置上传者
        Timestamp time=new Timestamp(System.currentTimeMillis());
        medicalpic.setUploadtime(time);//设置上传时间
        return medicalpicService.addOrUpdateApic(medicalpic);
    }*/

    /*
    图片处理成功后，state=1,
    这边要加入处理过程
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
    @GetMapping(value = "/MedicalToTrain/{id}")
    public void MedicalToTrain(@PathVariable(value = "id")Integer id){
        Medicalpic medicalpic=medicalpicService.findMedicalById(id).get();
        medicalpicService.MedicalToTrain(medicalpic);
    }

    /*
    根据id获取图片
     */
    @GetMapping(value = "/GetMedicalpicByid/{id}")
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

    /*
    获取自己的上传医学图像记录
     */
    @GetMapping(value = "/GetMyMedical/{name}")
    public List<Medicalpic> getMyTrain(@PathVariable("name") String name){
        User user=userService.findUserByName(name);

        return medicalpicService.findMyMedicalpic(user);
    }


    @GetMapping(value = "/UploadFile/{file}")
    public boolean uploadFile(@PathVariable("file") MultipartFile file) {
        if(!file.isEmpty()) {
            //获取文件名
            String filename = file.getOriginalFilename();

            // 将文件存放在/resources/static文件夹下
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";

            try {
                // 写入文件
                FileUtil.fileupload(file.getBytes(), path, filename);

                System.out.println("正在将文件  "+ filename + "  写入： " + path);

            }catch (IOException e) {
                e.printStackTrace();
            }

            // TODO 接下来，可以将路径存到数据库中
            String imgLoca = path+filename;
            String apiUrl = "http://localhost:8808/segment";
            String res = sendRequest(apiUrl, imgLoca, filename);
            System.out.println(res);
            // todo 存在一丢丢问题，图片处理完后没有放回到原文件夹，再去修改一下接口和脚本部分

            return true;
        }
        return false;
    }
    public String sendRequest(String imgurl, String imgLoca, String name) {
        URL url;
        HttpURLConnection connection = null;
        String urlParameters =
                "name=" + name + "&loca=" + imgLoca;

        System.out.println("正在访问url： "+imgurl+" 参数是： "+ urlParameters);

        try{
            //create connection
            url = new URL(imgurl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));

            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            return response.toString();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(connection != null ) {
                connection.disconnect();
            }
        }
        return "false";
    }
}


