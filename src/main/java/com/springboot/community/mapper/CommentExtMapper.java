package com.springboot.community.mapper;

import com.springboot.community.model.Comment;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}