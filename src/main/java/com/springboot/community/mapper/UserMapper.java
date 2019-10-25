package com.springboot.community.mapper;


import com.springboot.community.model.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/10/16 16:38
 * @Created by 猪刚鬣·李
 */
@Mapper
public interface UserMapper {
    //对象直接注入
   @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    //value需要用@Param方法注入
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
