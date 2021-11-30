package com.tap.word.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private String code = "";


    @ResponseBody
    @RequestMapping("/login")
    public void login(String user, String password, HttpServletResponse response){
        if (user == null || user == "" || password == null || password == ""){
            response.setStatus(401);
        }else if ("zhangsan".equals(user) && "ohmygod".equals(password)){
            code = user + password;
            response.addHeader("code", code);
            response.setStatus(302);
        }
    }


    @ResponseBody
    @RequestMapping("/oauthCode")
    public void oauthByCode(String oauthcode, HttpServletResponse response){
        if (oauthcode != null && oauthcode != ""){
            //step 7:使用code在8082验证登录合法性
            if (code.equals(oauthcode)){
                response.setStatus(200);
                response.addHeader("token", "success");
            }
        }else {
            response.setStatus(401);
        }
    }


}
