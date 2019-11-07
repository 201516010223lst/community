package com.springboot.community.service;

import com.springboot.community.dto.PaginationDTO;
import com.springboot.community.dto.QuestionDTO;
import com.springboot.community.mapper.QuestionMapper;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.Question;
import com.springboot.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public PaginationDTO list(Integer page, Integer size) {
        //创建一个分页DTO对象
        PaginationDTO paginationDTO = new PaginationDTO();
        /*拿到所有question表中所有列数*/
        Integer totalCount = questionMapper.count();
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
        List<Question> questions = questionMapper.list(offset, size);
        //创建一个问题DTO
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //循环问题
        for (Question question : questions) {
            //通过question.getCreator()关联question表  找到和user表中对应的user的id
            User user = userMapper.findById(question.getCreator());
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

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        /*拿到所有question表中所有列数*/
        Integer totalCount = questionMapper.countByUserId(userId);
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
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        //创建一个问题DTO
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //循环问题
        for (Question question : questions) {
            //通过question.getCreator()关联question表  找到和user表中对应的user的id
            User user = userMapper.findById(question.getCreator());
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

    public QuestionDTO getById(Integer id) {
        /*通过 id查找到问题*/
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        //BeanUtils.copyProperties(A,B);快速将A的属性拷贝到B上面
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
