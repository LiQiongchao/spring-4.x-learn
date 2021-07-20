package com.tamecode.dao;

import com.tamecode.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Liqc
 * @date 2021/7/20 19:33
 */
@Repository
public class LoginLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL = "INSET INTO t_login_log(user_id, ip, login_datetime) VALUES(?,?,?)";

    public void insertLoginLog(LoginLog loginLog) {
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
    }

}
