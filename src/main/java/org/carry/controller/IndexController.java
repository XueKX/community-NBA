package org.carry.controller;

import org.carry.mapper.UserMapper;
import org.carry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 16:25
 * @Description: ${Description}
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 从request中获取token,在数据库中查找，若存在，则返回数据库中的user信息,反之跳转登录页
     *
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }
        return "index";
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
