package org.carry.controller;

import org.carry.dto.AccessTokenDTO;
import org.carry.dto.GithubUser;
import org.carry.mapper.UserMapper;
import org.carry.model.User;
import org.carry.provide.GithubProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 16:25
 * @Description: ${Description}
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvide githubProvide;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    /**
     * 点击页面登录按钮后，根据GitHub指定方式获取授权信息逻辑。
     * Web服务器收到一个http请求，会针对每个请求创建一个HttpServletRequest和HttpServletResponse对象，
     * 向客户端发送数据找HttpServletResponse,从客户端取数据找HttpServletRequest.
     * 客户端浏览器发出的请求被封装成为一个HttpServletRequest对象，所有的信息包括请求的地址，请求的参数，提交的数据，上传的文件客户端的ip甚至客户端操作系统都包含在其内。
     * HttpServletResponse继承了ServletResponse接口，并提供了与Http协议有关的方法，这些方法的主要功能是设置HTTP状态码和管理Cookie。
     * HttpServletResponse对象代表服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法。
     *
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    //@RequestParam 是从url中取出所指定的参数
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletResponse response) {
        //TODO 为什么这个地方要写这个？
        // 因为你要获取accessToken，这5个参数是GitHub获取accessToken指定要带上的，然后调用getAccessToken方法获取accessToken
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        //获取token
        String accessToken = githubProvide.getAccessToken(accessTokenDTO);
        //获取githubUesr信息
        GithubUser githubUser = githubProvide.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            // 登录成功，将用户信息写入数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.inster(user);
            //TODO 这里是为什么将token传到response中，为什么IndexController 的 index 方法参数却是 HttpServletRequest？
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }
}
