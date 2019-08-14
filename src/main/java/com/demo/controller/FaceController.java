package com.demo.controller;

import com.demo.facelogin.FaceSearch;
import com.demo.utils.Base64Util;
import com.demo.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;


@Controller

public class FaceController {



    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/test1")
    public String test(@RequestParam("message") Integer message){
        if (message==1){
            return "success";
        } else return "fail";

    }


    @GetMapping("/face1")
    public String facemathchG(){
        return "index";
    }

    @PostMapping("/face")
      @ResponseBody
    //   public String  facematch( String message) throws IOException {
    public String  facematch(HttpServletRequest request) throws IOException {
             String despath = request.getParameter("message");  //从前端接受到的base64的数据
   //     System.out.println("----despath"+despath);
        FaceSearch serch=new FaceSearch();
  //     String result = serch.search(message);
        String result = serch.search(despath);
       // Integer result1 = Integer.parseInt(serch.search(despath));
      //  response.setContentType("text/html;charset=utf-8");
       // response.getWriter().print(result);
        System.out.println("------------Controller  "+result+"-----------");
        return result;
    }


}
