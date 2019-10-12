package com.springboot.community.dto;

/**
 * @Classname GithubUser
 * @Description TODO
 * @Date 2019/10/12 14:39
 * @Created by 猪刚鬣·李
 */
public class GithubUser {
    /*GitHub登录用户信息*/
    private String name;
    private long id;
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
