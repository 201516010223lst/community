package com.springboot.community.service;

import com.springboot.community.dto.PaginationDTO;
import com.springboot.community.dto.QuestionDTO;
import com.springboot.community.exception.CustomizeErrorCode;
import com.springboot.community.exception.CustomizeException;
import com.springboot.community.mapper.QuestionExtMapper;
import com.springboot.community.mapper.QuestionMapper;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.Question;
import com.springboot.community.model.QuestionExample;
import com.springboot.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname QuestionService
 * @Description TODO
 * @Date 2019/10/30 10:47
 * @Created by 猪刚鬣·李
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    //分页列表
    public PaginationDTO list(Integer page, Integer size) {
        //创建一个分页DTO对象
        PaginationDTO paginationDTO = new PaginationDTO();
        /*拿到所有question表中所有列数*/

        Integer totalPage;
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        //总共有多少个页面
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        /*页码小于1 则设置为1*/
        if (page < 1) {
            page = 1;
        }
        /*页码小于总页数 则设置为总页数*/
        if (page > totalPage) {
            page = totalPage;
        }
        /*调用setPagination方法判断分页显示结果 传入三个数 question表中所有列数  当前页  每页显示的数据*/
        paginationDTO.setPagination(totalPage, page);
        //偏移量size*(page-1)
        Integer offset = size * (page - 1);//偏移量  size为每一页显示有多少条数据
        //把查找到的问题列表数据放到list中  offset偏移量  size为每一页有多少条数据;
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        /* List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));*/
        //创建一个问题DTO
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //循环问题
        for (Question question : questions) {
            //通过question.getCreator()关联question表  找到和user表中对应的user的id
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //BeanUtils.copyProperties(A,B);快速将A的属性拷贝到B上面
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            //把关联查询到的数据插入到questionDTOList集合中
            questionDTOList.add(questionDTO);
        }
        //调用paginationDTO.setQuestions分页方法
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    //  我的问题列表
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        /*拿到所有question表中所有列数*/
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        questionExample.setOrderByClause("gmt_create desc");
        //questionExample.setOrderByClause("gmt_create desc");
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        Integer totalPage;
        //总共有多少个页面
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        /*页码小于1 则设置为1*/
        if (page < 1) {
            page = 1;
        }
        /*页码小于总页数 则设置为总页数*/
        if (page > totalPage) {
            page = totalPage;
        }
        /*调用setPagination方法判断分页显示结果 传入三个数 question表中所有列数  当前页  每页显示的数据*/
        paginationDTO.setPagination(totalPage, page);
        //偏移量size*(page-1)
        Integer offset = size * (page - 1);//偏移量  size为每一页显示有多少条数据
        //把查找到的问题列表数据放到list中  offset偏移量  size为每一页有多少条数据
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        //创建一个问题DTO
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //循环问题
        for (Question question : questions) {
            //通过question.getCreator()关联question表  找到和user表中对应的user的id
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //BeanUtils.copyProperties(A,B);快速将A的属性拷贝到B上面
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            //把关联查询到的数据插入到questionDTOList集合中
            questionDTOList.add(questionDTO);
        }
        //调用paginationDTO.setQuestions分页方法
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        /*通过 id查找到问题*/
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        //BeanUtils.copyProperties(A,B);快速将A的属性拷贝到B上面
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /*创建或者更新问题*/
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            /*插入问题*/
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            /*更新问题*/
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            //判断是否更新
            if (updated == 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    //阅读数累加
    public void incView(Long id) {
        Question question = new Question();
        /*查找到和数据库中相同的id*/
        question.setId(id);
        /*阅读数累加*/
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
    //相关问题
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOs = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOs;
    }
}
