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

import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.apache.http.*;


import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
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

        //获取文件名
        String filename = file.getOriginalFilename();

        // 将文件存放在/resources/static文件夹下
        String path = "";
        try {
            path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";

            byte[] bytes = file.getBytes();

            // 写入文件
            FileUtil.fileupload(file.getBytes(), path, filename);

            System.out.println("正在将文件  "+ filename + "  写入： " + path);

            // return "文件上传成功";

        } catch (IOException e) {
            e.printStackTrace();
            // return "后端异常...";
        }

        /* TODO 需要将处理的结果存入数据库并和上传者联系起来
        * 对singlefile方法还需要添加用户id的参数
        * 上传图片对应的类是meidicalpic
        * local 对应 imgLoca+filename
        * description可以存放图片的名字（filename）这里需要利用正则表达式去掉文件名的后缀
        * 因为在以后读取图片的操作时，需要根据图片名字取出相应数据
        * userid对应上传人的id
        * 其他的可填可不填
        * 还需要实现用户获取处理过的图片的方法
        */
        String imgLoca = path;
        String apiUrl = "http://localhost:8808/segment";
        System.out.println("正在处理...");
        String res = sendRequest(apiUrl, imgLoca, filename);
        System.out.println(res);


        return "success";
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