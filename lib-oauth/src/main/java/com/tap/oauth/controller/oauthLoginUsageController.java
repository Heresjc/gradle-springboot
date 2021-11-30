package com.tap.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class oauthLoginUsageController {

    static final String FUNC_SVC_PREFIX = "http://localhost:8081";

    @RequestMapping("/oauthLoginUsage")
    public void oauthLoginUsage(String token) throws URISyntaxException, IOException, InterruptedException {
        final var cli = HttpClient.newHttpClient();
        //step 8:登陆成功
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
    }


}
