package com.example.demo.Controller;

import com.example.demo.Domain.Postxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class GetTxtController {
        private static final Logger logger = LoggerFactory.getLogger(GetTxtController.class);
        public static String readTxt(File file) throws IOException {
            String s = "";
            InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader br = new BufferedReader(in);
            StringBuffer content = new StringBuffer();
            while ((s=br.readLine())!=null){
                content = content.append(s);
            }
            return content.toString();
        }

    @RequestMapping(value = "/getTXT/{name}")
    @ResponseBody
        public List<Postxt> getTxtByName(@PathVariable(value = "name",required = false) String filename){
        String s="";
        System.out.println(filename);
        List<Postxt> list=new ArrayList<Postxt>();
            try {
                //通过绝对路径获取文件
                s  = GetTxtController.readTxt(new File("src/trainPic/"+filename));
                logger.info(s);

                //spring boot中文件直接放在resources目录下
               // logger.info(s2);
            } catch (IOException e) {
                e.printStackTrace();
            }
           String [] arr = s.split("\\s+");
        int flag=0;double x=0,y=0,w=0,h=0;
             for(int i=0;i<arr.length;i++){


               if(isNumeric(arr[i])) {
                   int num = Integer.parseInt(arr[i]);
                   if(flag==0){
                       x=num*0.5;
                   }
                   else if(flag==1){
                       y=num*0.5;
                   }
                   else if(flag==2){
                       w=num*0.5;
                   }
                   else if(flag==3){
                       h=num*0.5;
                       Postxt postxtt=new Postxt((int)x,(int)y,(int)w,(int)h,0);
                       flag=-1;
                       list.add(postxtt);
                   }
                   flag+=1;
               }
             }
            return list;
        }

    public static boolean isNumeric(String str){
           for (int i = str.length();--i>=0;){
                   if (!Character.isDigit(str.charAt(i))){
                       return false;
                       }
                }
            return true;
         }
}
