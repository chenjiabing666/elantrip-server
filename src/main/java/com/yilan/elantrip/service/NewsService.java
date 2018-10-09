package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.News;
import com.yilan.elantrip.domain.NewsImage;
import com.yilan.elantrip.util.PagingTool;

/**
 * 用户的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface NewsService {

	/**
	 * 添加公告
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object addNews(News news,List<NewsImage> newsImages)throws Exception;
	/**
	 * 添加公告
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object delNews(int newsId)throws Exception;
	/**
	 * 修改公告
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object modifyNews(News news)throws Exception;

	/**
	 * 获取公告详细信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @throws Exception
	 */
	Object getNewsById(int newsId)throws Exception;

	/**
	 * 获取公告总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取公告列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<News> getNewsList(PagingTool pagingTool)throws Exception;


}
