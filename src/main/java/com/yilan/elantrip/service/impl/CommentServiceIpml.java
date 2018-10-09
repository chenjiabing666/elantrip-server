package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.CommentMapper;
import com.yilan.elantrip.domain.Comment;
import com.yilan.elantrip.service.CommentService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service("CommentService")
public class CommentServiceIpml implements CommentService{
    
	@Resource
	private CommentMapper commentMapper;
	
	
	
	public CommentMapper getCommentMapper() {
		return commentMapper;
	}

	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public Object addComment(Comment comment) throws Exception {
	    
		int count = 0;
		try {
			count = commentMapper.insertSelective(comment);
			if (count < 0) {
				throw new Exception("添加评论失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加评论异常！");
		}
		return count;
	}
	
//	@Override
//	public Object getCommentByCommentId(int commentId){
//			ResultInfo resultInfo = new ResultInfo();
//			 Comment comment = null;
//			try {
//				comment =  commentMapper.selectByPrimaryKey(commentId);
//			} catch (Exception e) {
//				e.printStackTrace();
//				resultInfo.setCode("-1");
//				throw new ApplicationContextException("获取详情异常");
//			}
//			resultInfo.setMessage("获取成功");
//			resultInfo.setResult(comment);
//			return resultInfo;
//		
//		}
	

	@Override
	public Integer deleteComment(int commentId) throws Exception {
		int count = 0;
		try {
			count = commentMapper.deleteByPrimaryKey(commentId);
			if (count < 0) {
				throw new Exception("删除评论失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("删除评论异常！");
		}
		return count;
	}
	
	@Override
	public Integer countTotal(PagingTool pagingTool) {
		int count = 0;

		try {
			count = commentMapper.countTotal(pagingTool);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评论总数异常！");
		}
		return count;
	}
    //获取广告列表
	@Override
	public List<Comment> getCommentList(PagingTool pagingTool) {
		List<Comment> commentList = null;

		try {
			commentList = commentMapper.getCommentList(pagingTool);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取评论列表异常");
		}
		return commentList;
	}

	@Override
	public Object commentReply(Comment comment) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		try {
			count = commentMapper.updateByPrimaryKey(comment);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("修改新闻失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("修改新闻异常");
		}
		return count;
	}

	@Override
	public Object getCommentByCommentId(int commentId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		 Comment comment = null;
		try {
			comment =  commentMapper.selectByPrimaryKey(commentId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(comment);
		return resultInfo;
	}


	}

   
	
	


