package com.springboot.community.dto;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.alibaba.fastjson.serializer.FieldSerializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PaginationDTO
 * @Description TODO
 * @Date 2019/10/30 18:48
 * @Created by 猪刚鬣·李
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;/*问题列表*/
    private boolean showPrevious;//前一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//最后一页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//所有页集合
    private Integer totalPage;//总共有多少个页面
    /*分页*/
    //totalCount为question表中的所有列数总和
    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            //往前头部插入
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            //往后尾部插入
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
