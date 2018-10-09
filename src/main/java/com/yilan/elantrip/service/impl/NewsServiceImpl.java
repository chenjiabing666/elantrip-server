package com.yilan.elantrip.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.NewsImageMapper;
import com.yilan.elantrip.dao.NewsMapper;
import com.yilan.elantrip.domain.News;
import com.yilan.elantrip.domain.NewsImage;
import com.yilan.elantrip.domain.rs.RSnewsImage;
import com.yilan.elantrip.service.NewsService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;



@Service
public class NewsServiceImpl implements NewsService{
	
	@Resource
	private NewsMapper newsMapper;
	@Resource
	private NewsImageMapper newsImageMapper;
	@Override
	public Object addNews(News news,List<NewsImage> newsImages) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		// 插入消息
		int count = newsMapper.insertSelective(news);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		// 插入图片
		for (NewsImage newsImage : newsImages) {
			newsImage.setNewsId(news.getNewsId());
			newsImage.setCreatedDate(new Date());
			int count1 = newsImageMapper.insertSelective(newsImage);
			if (count1 == 0) {
				throw new RuntimeException();
			}
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object delNews(int newsId) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		try {
			count = newsMapper.deleteByPrimaryKey(newsId);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("删除新闻失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("删除新闻异常");
		}
		// 删除图片
		int count1 = 0;
		try {
			count1 = newsImageMapper.deleteByNewsId(newsId);
			if (count1 < 0) {
				resultInfo.setCode("-1");
				throw new Exception("删除新闻图片失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("删除新闻图片异常");
		}
		return count;
	}

	@Override
	public Object modifyNews(News news) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		try {
			count = newsMapper.updateByPrimaryKeySelective(news);
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
	public Object getNewsById(int newsId) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		RSnewsImage rSnewsImage = null;
		try {
			rSnewsImage =  newsMapper.selectNewsDetail(newsId);
			System.out.println("111");
			System.out.println(rSnewsImage);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(rSnewsImage);
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = newsMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<News> getNewsList(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<News> newsList = null;
		try {
			newsList = newsMapper.selectNewsList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取新闻列表异常");
		}

		return newsList;
	}

	
}
