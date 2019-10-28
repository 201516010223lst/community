package com.springboot.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname Quesption
 * @Description TODO
 * @Date 2019/10/28 15:26
 * @Created by 猪刚鬣·李
 */
@Mapper
public interface Question {
    @Insert("insert into(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
