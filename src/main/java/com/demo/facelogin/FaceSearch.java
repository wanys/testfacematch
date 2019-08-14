package com.demo.facelogin;


import com.demo.utils.GsonUtils;
import com.demo.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import java.util.Map;

public class FaceSearch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String  search(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image);
            map1.put("liveness_control", "NORMAL");
            map1.put("group_id_list", "test");
            map1.put("image_type", "BASE64");
            map1.put("quality_control", "LOW");
            
            String param = GsonUtils.toJson(map1);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            AuthService authService=new AuthService();
            String accessToken = authService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println("----1----"+result);
            JSONObject objectResult = new JSONObject(result);
            System.out.println("----2----"+objectResult);
            int error_code=objectResult.getInt("error_code");
            Double score=0.0;
            if(error_code==0){
                JSONObject jsonResult = objectResult.getJSONObject("result");
                System.out.println("----3----"+jsonResult);
                JSONArray arrayUserList = jsonResult.getJSONArray("user_list");
                System.out.println("----4----"+arrayUserList);
                JSONObject scoreInfo =arrayUserList.getJSONObject(0);
                score =scoreInfo.getDouble("score");
                System.out.println("--score---"+score);
            }else {
                String error_msg=objectResult.getString("error_msg");
                System.out.println("error_msg"+error_msg);
            }

            /*JSONObject objectresult = new JSONObject(result);
             System.out.println("---object"+objectresult);
            JSONObject jsonresult = objectresult.getJSONObject("result");
            System.out.println("--resultob"+jsonresult);
            JSONArray Arrayuser_list = jsonresult.getJSONArray("user_list");
            JSONString scored =  Arrayuser_list.getString("score");
            System.out.println("--score"+scored);*/

          //   String score = result.split(",")[5].split(",")[1].split(",")[3].split(":")[1];
          //  System.out.println(score);
            return score.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public static void main(String[] args) {
        facematch.detect();
    }*/
}

