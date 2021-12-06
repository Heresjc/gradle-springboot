package com.tap.word.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@RestController
public class LoginController {

    private String code = "";

    static final String OAUTH_SVC_PREFIX = "http://localhost:8082";
    static final String FUNC_SVC_PREFIX = "http://localhost:8081";

    @ResponseBody
    @RequestMapping("/loginCheck")
    public void loginCheck(HttpServletResponse response){
        response.setStatus(302);
        response.addHeader("location", OAUTH_SVC_PREFIX + "/login?url=" + URLEncoder.encode(FUNC_SVC_PREFIX+"/oauthByCode", StandardCharsets.UTF_8));
    }



    @ResponseBody
    @RequestMapping("/login")
    public void login(String user, String password, String url, HttpServletResponse response){
        if (user == null || user == "" || password == null || password == ""){
            response.setStatus(401);
        }else if ("zhangsan".equals(user) && "ohmygod".equals(password)){
            //通过用户名查询是否已经存在
            //如果用户名不存在，提示并跳转到登陆页面
            //如果用户名存在，校验密码是否正确
            //如果密码不正确，提示
            //如果密码正确，生成code
            code = user + password;
            //将新生成的code存入数据库
            //返回code
            response.addHeader("location", url + "?code=" + code);
            response.setStatus(302);
        }
    }


    @ResponseBody
    @RequestMapping("/oauthCode")
    public void oauthByCode(String oauthcode, String token, HttpServletResponse response){
        if (oauthcode != null && oauthcode != ""){
            //step 7:使用code在8082验证登录合法性
            if (code != "" && code != null && code != "null"){
                //通过code查询对应的用户状态
                //0,等待验证的用户，生成token，并将token等信息存入数据库，同时将token返回到FUNC_SVC_PREFIX，同时带入跳转到登陆成功后页面的location
                response.setStatus(200);
                response.addHeader("token", UUID.randomUUID().toString());
                //token过期时间，1.每次都带着，和服务器当前时间作比较；2.存入数据库，比较时取出
                response.addHeader("expired_time", String.valueOf(getCurrentTimeMillis()));
                response.addHeader("refresh_token", UUID.randomUUID().toString());
                response.addHeader("location","");
                // 1:正常状态,查询此token是否过期，如果过期，跳转到登陆页面，未过期，正常执行，如果用户一直处于操作状态下，不能过期，长时间不操作才能过期
                // 2：用户被锁定.提示登录异常
            }
        }else {
            response.setStatus(401);
        }
    }


    /**
     * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
     *
     * @return 当前日期时间
     */
    public static String getCurDateTimeStr() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        String str = timestamp.toString();
        return new StringBuffer().append(datetime).toString();
    }

    /**
     * 获取系统当前的整数时间戳
     * @return
     */
    public final long getCurrentTimeMillis(){
        return System.currentTimeMillis() / 1000;
    }

}
