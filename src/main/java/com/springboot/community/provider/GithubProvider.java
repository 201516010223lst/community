package com.springboot.community.provider;

import com.alibaba.fastjson.JSON;
import com.springboot.community.dto.AccessTokenDTO;
import com.springboot.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Classname GithubProvider
 * @Description TODO
 * @Date 2019/10/12 15:59
 * @Created by 猪刚鬣·李
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                /*System.out.println(string);*/
                String token = string.split("&")[0].split("=")[1];
                /* System.out.println(token);*/
                return token;
                /*return string;*/
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }

    /*获取GitHub登录用户信息*/
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
           /* System.out.println(githubUser);*/
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
