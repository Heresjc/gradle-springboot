package com.tap.oauth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class OauthUsage {

    static final String OAUTH_SVC_PREFIX = "http://localhost:8082";
    static final String FUNC_SVC_PREFIX = "http://localhost:8081";


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        final var cli = HttpClient.newHttpClient();

        //step 0:需要确认用户合法性，此模块为非登录状态
        final var step0VerifyLoginStatus = HttpRequest.newBuilder()
                .uri(new URI(FUNC_SVC_PREFIX + "/myAccount?token="))
                .GET()
                .build();
        final var step0Response = cli.send(step0VerifyLoginStatus, HttpResponse.BodyHandlers.ofString());

        assert (step0Response.statusCode() == 401);
        // step 1: 要向服务供应商 8081 发起登录
        final var s1AuthRequest = HttpRequest.newBuilder()
                .uri(new URI(OAUTH_SVC_PREFIX + "/loginCheck"))
                .GET()
                .build();
        final var s2Response = cli
                .send(s1AuthRequest, HttpResponse.BodyHandlers.ofString());
        //step 2:302跳转
        assert (s2Response.statusCode() == 302);
        assert(
                s2Response.headers().firstValue("location").get()
                        .equals(OAUTH_SVC_PREFIX + "/login?url=" + URLEncoder.encode(FUNC_SVC_PREFIX+"/oauthByCode", StandardCharsets.UTF_8)));

        //step 3 4:授权中心 8082 登录
        final var step2CallbackUrl = s2Response.headers().firstValue("location").get();
        final var step3and4LoginRequest = HttpRequest.newBuilder()
                .uri(new URI(step2CallbackUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("user=zhangsan&password=ohmygod"))
                .build();
        final var step5Response = cli
                .send(step3and4LoginRequest, HttpResponse.BodyHandlers.ofString());

        //step 5:返回登录是否成功的code
        assert(step5Response.statusCode() == 302);
        final var step5CallbackUrl = step5Response.headers().firstValue("location").get();
        assert(step5CallbackUrl.startsWith(FUNC_SVC_PREFIX));
        final var callbackUrlMatcher = Pattern.compile("\\?code=(.*)")
                .matcher(step5CallbackUrl);
        assert(callbackUrlMatcher.matches());

        //step 6:向服务提供者 8081 请求登录
        final var step6CodeAuthRequest = HttpRequest.newBuilder()
                .uri(new URI(step5CallbackUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        final var step8Response = cli.send(step6CodeAuthRequest, HttpResponse.BodyHandlers.ofString());
    }

    //注册
}
