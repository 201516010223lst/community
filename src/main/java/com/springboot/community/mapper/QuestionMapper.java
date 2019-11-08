package com.springboot.community.mapper;

import com.springboot.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname Quesption
 * @Description TODO
 * @Date 2019/10/28 15:26
 * @Created by 猪刚鬣·李
 */
@Mapper
public interface QuestionMapper{

        /*插入问题*/
        @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag)  values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
        void create(Question question);
        /*更新问题*/
        @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
        void update(Question question);
        /*查找问题 分页*/
        @Select("select * from question limit #{offset},#{size}")
        List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
        /*查找问题所有列数*/
        @Select("select count(1) from question")
        Integer count();
        /*通过user表id在question表中查找出我的问题*/
        @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
        List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
        /*查找我的问题的所有列数*/
        @Select("select count(1) from question where creator=#{userId}")
        Integer countByUserId(@Param(value = "userId") Integer userId);
        /*通过id查找问题*/
        @Select("select * from question where id=#{id} ")
        Question getById(@Param(value = "id") Integer id);

}
