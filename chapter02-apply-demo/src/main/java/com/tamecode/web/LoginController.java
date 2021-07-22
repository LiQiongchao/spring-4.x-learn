package com.tamecode.web;

import com.tamecode.domain.LoginCommand;
import com.tamecode.domain.User;
import com.tamecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Liqc
 * @date 2021/7/21 18:48
 */
@Controller // ①标注成为一个 Spring MVC 的 Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // ② 负责处理 /index.html 的请求
    @RequestMapping(value = "index.html")
    public String loginPage() {
        return "login";
    }

    // ③ 负责处理 /loginCheck.html 的请求
    @RequestMapping(value = "loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            User user = userService.findUserByUsername(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

}
