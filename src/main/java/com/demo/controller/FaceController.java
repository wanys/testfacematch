package com.demo.controller;

import com.demo.facelogin.FaceSearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Controller
public class FaceController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "success";
    }

    @PostMapping("/face")
    public String facematch(HttpServletRequest request, HttpServletResponse response){
        String despath = request.getParameter("message");//从前端接受到的base64的数据

        FaceSearch serch=new FaceSearch();
        Integer result = Integer.valueOf(serch.detect(despath));
        if(result>80){
            return "success";
        } else return "fail";

    }


}
