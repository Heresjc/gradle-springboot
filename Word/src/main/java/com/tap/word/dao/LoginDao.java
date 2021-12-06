package com.tap.word.dao;

import com.tap.word.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginDao extends JpaRepository<UserInfo, Long> {

    /**
     * 通过token查询对应用户是否有效
     * @param token
     * @return
     */
    @Query("select state from UserInfo where token = :token")
    String selectStateBytoken(@Param("token") String token);

}
