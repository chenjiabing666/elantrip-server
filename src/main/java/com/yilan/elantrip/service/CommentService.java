package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Comment;
import com.yilan.elantrip.util.PagingTool;

@Transactional
public interface CommentService {
	
	Object addComment(Comment comment) throws Exception;
	
	Integer deleteComment(int commentId) throws Exception;
	
	Integer countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取评论列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<Comment> getCommentList(PagingTool pagingTool) throws Exception;
	
	Object commentReply(Comment comment) throws Exception;
	
	Object getCommentByCommentId(int commentId) throws Exception;
	
}
