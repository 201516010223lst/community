package com.springboot.community.mapper;

import com.springboot.community.dto.QuestionQueryDTO;
import com.springboot.community.model.Question;

import java.util.List;

/*mybatis generator集成的QuestionMapper*/
public interface QuestionExtMapper {
    /**/
    int incView(Question question);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}