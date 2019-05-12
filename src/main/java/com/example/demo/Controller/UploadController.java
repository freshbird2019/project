package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.apache.http.*;


import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.util.Objects;



/**

 * Created by IntelliJ IDEA.

 *

 * @author luoliang

 * @date 2018/2/12

 **/

@RestController

@RequestMapping("/upload")

@CrossOrigin

public class UploadController {

    @Value("${prop.upload-folder}")

    private String UPLOAD_FOLDER;

    private Logger logger = LoggerFactory.getLogger(UploadController.class);



    @PostMapping("/singlefile")

    public Object singleFileUpload(MultipartFile file) {
       // logger.debug("传入的文件参数：{}", JSON.toJSONString(file, true));

        if (Objects.isNull(file) || file.isEmpty()) {

            logger.error("文件为空");

            return "文件为空，请重新上传";

        }



        try {


            byte[] bytes = file.getBytes();

            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());

            //如果没有files文件夹，则创建

            if (!Files.isWritable(path)) {

                Files.createDirectories(Paths.get(UPLOAD_FOLDER));

            }

            //文件写入指定路径

            Files.write(path, bytes);

            logger.debug("文件写入成功...");

            // 如果文件上传成功则唤起调用python接口


            return "文件上传成功";

        } catch (IOException e) {

            e.printStackTrace();

            return "后端异常...";

        }

    }


    //TODO 暂未测试功能正确性
    public String processImg(String filepath, String filename) {
        // filepath是图片存放的绝对地址
        // filename是图片的名字
        // 最后的处理结果会放在图片同一目录下，名字相同，但是文件格式不一样所以没关系

        // python程序的接口地址
        String url = "http://localhost:8808/segment?"+"name=filename&"+"loca=filepath";

        // 获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //生成post请求
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {

            //执行请求
            response = httpClient.execute(httpPost);

        }catch(IOException e ) {
            e.printStackTrace();
        }

        HttpEntity entity = response.getEntity();
        String result = null;

        try {

            result = EntityUtils.toString(entity);
        }catch(ParseException | IOException e) {

            e.printStackTrace();
        }

        return result;




    }

}