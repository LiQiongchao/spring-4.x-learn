package com.tamecode.dao;

import com.tamecode.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Liqc
 * @date 2021/7/20 19:03
 */
@Repository // 通过 Spring 注解定义一个 DAO
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    // 根据用户名查询用户的sql
    private final static String MATCH_COUNT_SQL = "SELECT * FROM t_user WHERE user_name = ?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?, last_ip=?, credits=? WHERE user_id=?";

    @Autowired // 自动注入 JdbcTemplate 的 Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String username, String password) {
        String sqlStr = "SELECT COUNT(*) FROM t_user WHERE user_name = ? and password = ?";
        return jdbcTemplate.queryForObject(sqlStr, Integer.class, username, password);
    }

    public User findUserByUsername(final String username) {
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{username}, new RowCallbackHandler() {
            // 匿名类方式实现回调函数
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(username);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId());
    }

}
