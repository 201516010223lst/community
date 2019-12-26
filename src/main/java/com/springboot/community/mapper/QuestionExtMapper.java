package com.springboot.community.mapper;

import com.springboot.community.model.Question;
import com.springboot.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/*mybatis generator集成的QuestionMapper*/
public interface QuestionExtMapper {
    /**/
    int incView(Question question);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);
}