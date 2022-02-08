package com.cos.blog.test;

import com.cos.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class FlaskTestController {

    @GetMapping("/auth/test")
    public ModelAndView Test(User user){
        ModelAndView mav = new ModelAndView();

        String url = "http://apis.data.go.kr/9760000/ElcntInfoInqireService/getElpcElcntInfoInqire";
        String sb = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

            String line = null;

            while((line = br.readLine()) != null){
                sb += line + "\n";
            }
            System.out.println("==========br========="+sb);
            if(sb.contains("ok")){
                System.out.println("test");
            }
            br.close();
            System.out.println(""+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        mav.addObject("test1",sb);
        mav.addObject("fail",false);
        mav.setViewName("test");
        return mav;
    }
}
