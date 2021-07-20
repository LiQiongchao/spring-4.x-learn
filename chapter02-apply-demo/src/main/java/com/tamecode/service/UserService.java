package com.tamecode.service;

import com.tamecode.dao.LoginLogDao;
import com.tamecode.dao.UserDao;
import com.tamecode.domain.LoginLog;
import com.tamecode.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liqc
 * @date 2021/7/20 19:40
 */
@Service // 将 UserService 标注为一个服务层的 Bean
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;


    public boolean hasMatchUser(String username, String password) {
        return userDao.getMatchCount(username, password) > 0;
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    /**
     * 密码一般不会明文保存，一般是使用 MD5 摘要保存，不可逆。
     * 为了防止暴力破解，一般登录时，会使用图片验证方式。
     * @param user
     */
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

}
