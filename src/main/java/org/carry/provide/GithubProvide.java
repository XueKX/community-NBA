package org.carry.provide;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.carry.dto.AccessTokenDTO;
import org.carry.dto.GithubUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 21:54
 * @Description: 通过OKhttp3 请求github指定地址获取token
 */
@Component
public class GithubProvide {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .header("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param accessToken
     * @return 携带token请求GitHub指定地址，获取user信息
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
//                .url("https://api.github.com/user")
//                .header("Authorization", accessToken+" OAUTH-TOKEN")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
