package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yilan.elantrip.domain.News;
import com.yilan.elantrip.domain.NewsImage;
import com.yilan.elantrip.service.NewsService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

/**
 * 新闻的Controller
 * 
 * @author Administrator
 *
 */

@Controller
public class NewsController {

	@Resource
	private NewsService newsService;

	/**
	 * 添加新闻/公告
	 * 
	 * @param newsTitle
	 *            主题
	 * @param newsType
	 *            类型
	 * @param newsContent
	 *            内容
	 * @param adminName
	 *            发布人
	 * @param newsImage
	 *            图片
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/news/addNews")
	public @ResponseBody Object addNews(@RequestParam(value = "newsImage", required = false) MultipartFile[] newsImage,
			HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		String newsTitle = request.getParameter("newsTitle");
		String newsType = request.getParameter("newsType");
		String newsContent = request.getParameter("newsContent");
		String adminName = request.getParameter("adminName");

		if (newsTitle == null || newsTitle.equals("")) {
			reInfo.setMessage("新闻主题不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (newsType == null || newsType.equals("")) {
			reInfo.setMessage("新闻类型不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (newsContent == null || newsContent.equals("")) {
			reInfo.setMessage("新闻内容不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (adminName == null || adminName.equals("")) {
			reInfo.setMessage("发布人不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (newsImage != null && newsImage.length > 5) {
			reInfo.setCode("-1");
			reInfo.setMessage("最多只能上传5张图片");
			return reInfo;
		}
		// 处理图片
		List<NewsImage> newImages = new ArrayList<NewsImage>();
		if (newsImage != null) {
			for (MultipartFile imageFile : newsImage) {
				String newsImageFileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("news", newsImageFileName, imageFile);
					NewsImage image = new NewsImage();
					image.setImageUrl(imageUrl);
					image.setCreatedDate(new Date());
					newImages.add(image);
				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setCode("-1");
					reInfo.setMessage("上传新闻图片失败");
					return reInfo;
				}
			}
		} else {
			NewsImage image = new NewsImage();
			image.setImageUrl("null");
			newImages.add(image);
		}

		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setNewsType(Integer.parseInt(newsType));
		news.setNewsConent(newsContent);
		news.setAdminName(adminName);
		news.setActivated(1);
		news.setCreatedDate(new Date());

		try {
			Object object = newsService.addNews(news, newImages);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setCode("-1");
			reInfo.setMessage("添加新闻异常");
			return reInfo;
		}

	}

	/**
	 * 获取新闻列表
	 * 
	 * @param newsTitle
	 *            主题
	 * @param newsType
	 *            类型
	 * @param adminName
	 *            发布人
	 * @param activated
	 *            是否生效
	 * @param pageNum
	 *            起始页
	 * @param pageSize
	 *            每页显示数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/news/getNewsList")
	public @ResponseBody Object getNewsList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String newsTitle = request.getParameter("newsTitle");
		String newsType = request.getParameter("newsType");
		String adminName = request.getParameter("adminName");
		String activated = request.getParameter("activated");
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (newsTitle != null && !newsTitle.equals("")) {
			params.put("newsTitle", newsTitle);
		}
		if (newsType != null && !newsType.equals("")) {
			params.put("newsType", newsType);
		}
		if (adminName != null && !adminName.equals("")) {
			params.put("adminName", adminName);
		}
		if (activated != null && !activated.equals("")) {
			params.put("activated", activated);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) newsService.countTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}

		List<News> newsList = null;
		try {
			newsList = newsService.getNewsList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (newsList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<News>());
			return reInfo;
		}
		reInfo.setResult(newsList);
		reInfo.setTotal(totalCount);
		reInfo.setCode("0");
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 修改新闻
	 * 
	 * @param newsId
	 *            公告ID
	 * @param newsTitle
	 *            主题
	 * @param newsType
	 *            类型
	 * @param newsContent
	 *            内容
	 * @param adminName
	 *            发布人
	 * @param activated
	 *            是否生效
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/news/modifyNews")
	public @ResponseBody Object modifyNews(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		String newsId = request.getParameter("newsId");
		String newsTitle = request.getParameter("newsTitle");
		String newsType = request.getParameter("newsType");
		String adminName = request.getParameter("adminName");
		String activated = request.getParameter("activated");
		String newsContent = request.getParameter("newsContent");
		News news = new News();
		news.setNewsConent(newsContent);
		news.setUpdatedDate(new Date());
		if (newsId == null || newsId.equals("")) {
			reInfo.setMessage("ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		news.setNewsId(Integer.parseInt(newsId));
		if (newsTitle != null && !newsTitle.equals("")) {
			news.setNewsTitle(newsTitle);
		}
		if (newsType != null && !newsType.equals("")) {
			news.setNewsType(Integer.parseInt(newsType));
		}
		if (adminName != null && !adminName.equals("")) {
			news.setAdminName(adminName);
		}
		if (activated != null && !activated.equals("")) {
			news.setActivated(Integer.parseInt(activated));
		}
		if (newsContent != null && !newsContent.equals("")) {
			news.setNewsConent(newsContent);
		}
		int count = 0;
		try {
			count = (int) newsService.modifyNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setMessage("修改信息异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (count < 1) {
			reInfo.setMessage("修改信息失败！");
			reInfo.setCode("-1");
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setMessage("修改信息成功！");
		return reInfo;
	}

	/**
	 * 删除新闻
	 * 
	 * @param newsId
	 *            新闻Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/news/deleteNews")
	public @ResponseBody Object deleteNews(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {

		ResultInfo reInfo = new ResultInfo(); // 封装结果集

		String newsId = request.getParameter("newsId");
		newsId = "12";
		if (newsId == null || newsId.equals("")) {
			reInfo.setMessage("公告Id不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		int count = 0;
		try {
			count = (int) newsService.delNews(Integer.parseInt(newsId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setMessage("删除公告异常!");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (count > 0) {
			reInfo.setCode("0");
			reInfo.setMessage("删除公告成功！");
		} else {
			reInfo.setMessage("删除公告失败！");
			reInfo.setCode("-1");
			return reInfo;
		}
		return reInfo;
	}

	/**
	 * 获取新闻详情
	 * 
	 * @param newsId
	 *            新闻Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/news/getNews")
	public @ResponseBody Object getNewsById(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		String newsId = request.getParameter("newsId");
		if (newsId == null || newsId.equals("")) {
			reInfo.setMessage("公告ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		Object rSnewsImage = null;
		try {
			rSnewsImage = newsService.getNewsById(Integer.parseInt(newsId));
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setMessage("获取公告信息失败！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (rSnewsImage == null) {
			reInfo.setMessage("公告信息不存在！");
			reInfo.setResult(new News());
			reInfo.setTotal(0);
			return reInfo;
		}
		reInfo.setResult(rSnewsImage);
		reInfo.setMessage("获取公告信息成功！");
		return reInfo;
	}
}
