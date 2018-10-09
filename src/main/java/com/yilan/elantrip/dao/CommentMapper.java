package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Comment;
import com.yilan.elantrip.util.PagingTool;


public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    int countTotal(PagingTool pagingTool);
    
    List<Comment> getCommentList(PagingTool pagingTool);
    
}