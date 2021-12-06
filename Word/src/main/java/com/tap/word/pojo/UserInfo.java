package com.tap.word.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private long uid;

    @Column(unique = true)
    private String username;

    private String password;

    private String code;

    private String token;
    //token过期时间
    private String oauthTime;

    private String refresh_token;

    @Column(insertable = false,columnDefinition = "String default 2")
    private String state;//用户状态,0,等待验证的用户 1:正常状态,2：用户被锁定.


}