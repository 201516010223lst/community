package com.springboot.community.mapper;


import com.springboot.community.model.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.*;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/10/16 16:38
 * @Created by 猪刚鬣·李
 */
@Mapper
public interface UserMapper {
    //对象直接注入
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    //value需要用@Param方法注入
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    /*通过id关联*/
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
    /*通过user表id查找*/
    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);
    /*更新问题*/
    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
