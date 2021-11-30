package com.tap.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class LoginCheckController {

    static final String FUNC_SVC_PREFIX = "http://localhost:8081";

    @ResponseBody
    @RequestMapping("/myAccount")
    public void myaccount(String token, HttpServletResponse response){
        if (token == null || token == ""){
            response.setStatus(401);
        }else if ("success".equals(token)){
            response.setStatus(200);
        }
    }

    @ResponseBody
    @RequestMapping("/loginCheck")
    public void loginCheck(HttpServletResponse response){
        response.setStatus(302);
    }


    @ResponseBody
    @RequestMapping("/oauthByCode")
    public void oauthByCode(String code, HttpServletResponse response) throws URISyntaxException, IOException, InterruptedException {
        if (code != null && code != ""){
            //step 7:使用code在8082验证登录合法性
            final var cli = HttpClient.newHttpClient();
            final var step7CodeAuthRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8082/oauthCode"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("oauthcode="+code))
                    .build();
            final var step8Response = cli.send(step7CodeAuthRequest, HttpResponse.BodyHandlers.ofString());
            assert(step8Response.statusCode() == 200);
            //step 8:登陆成功
            String token = step8Response.headers().firstValue("token").get();
            final var step9VerifyLoginStatus = HttpRequest.newBuilder()
                    .uri(new URI(FUNC_SVC_PREFIX + "/myAccount?token="+token))
                    .GET()
                    .build();
            final var step9Response = cli
                    .send(step9VerifyLoginStatus,
                            HttpResponse
                                    .BodyHandlers.ofString());
            assert (step9Response.statusCode() == 200);
            System.out.println("登陆成功！！！");
        }else {
            response.setStatus(401);
        }
    }


}
