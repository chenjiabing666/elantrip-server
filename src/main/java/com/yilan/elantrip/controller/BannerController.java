package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.domain.NewsImage;
import com.yilan.elantrip.service.BannerService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

@RestController
public class BannerController {
	//注入
	@Resource
	private BannerService bannerService;
    
	public BannerService getBannerService() {
		return bannerService;
	}
   
	@Autowired
	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}

	/**
	 * 添加广告
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/banner/addBanner")
	public @ResponseBody Object addBanner(HttpServletRequest request,
			@RequestParam(value = "bannerImage", required = false) MultipartFile bannerImage) {
		//封装结果集
		ResultInfo rsInfo = new ResultInfo();
        //获取广告参数
		String bannerType = request.getParameter("bannerType");
		String locationUrl = request.getParameter("locationUrl");
		String jumpUrl = request.getParameter("jumpUrl");
		String location = request.getParameter("location");
		String activated = request.getParameter("activated");
		
		
        //参数校验
		if (bannerType == null || bannerType.equals("")) {
			rsInfo.setMessage("广告类型不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (locationUrl == null || locationUrl.equals("")) {
			rsInfo.setMessage("放置页面不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (jumpUrl == null || jumpUrl.equals("")) {
			rsInfo.setMessage("跳转页面不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (location == null || location.equals("")) {
			rsInfo.setMessage("放置位置不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (activated == null || activated.equals("")) {
			rsInfo.setMessage("放置位置不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		
        
		Banner banner = new Banner();


		// 处理图片
		if (bannerImage != null) {
			String bannerImageFile = System.currentTimeMillis() + bannerImage.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("banner", bannerImageFile, bannerImage);
				NewsImage image = new NewsImage();
				image.setImageUrl(imageUrl);
				image.setCreatedDate(new Date());
			} catch (Exception e) {
				e.printStackTrace();
				rsInfo.setCode("-1");
				rsInfo.setMessage("上传广告图片失败");
				return rsInfo;	
		}
			
			banner.setImageUrl("111111111");
		}
	
	    banner.setUpdatedDate(new Date());
		banner.setCreatedDate(new Date());
		banner.setActivated(Integer.valueOf(activated));
		banner.setLocationUrl(locationUrl);
		banner.setBannerType(Integer.valueOf(bannerType));
		banner.setJumpUrl(jumpUrl);
		banner.setLocation(Integer.valueOf(location));
		banner.setCreatedDate(new Date());
		
		try {
			Object object = bannerService.addBanner(banner);
			return object;
		} catch (Exception e) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("添加广告异常");
			return rsInfo;
		}
	}

	/**
	 * 修改广告信息
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/banner/modifyBanner")
	public @ResponseBody Object modifyBanner(HttpServletRequest request,
			@RequestParam(value = "bannerImage", required = false) MultipartFile bannerImage
			) {
		ResultInfo rsInfo = new ResultInfo();//注入


		String bannerType = request.getParameter("bannerType");//获取广告类型
		String locationUrl = request.getParameter("locationUrl");//获取放置页面
		String jumpUrl = request.getParameter("jumpUrl");//获取跳转页面
		String location = request.getParameter("location");//获取放置位置
		String activated = request.getParameter("activated");//获取是否激活
		String bannerId = request.getParameter("bannerId");

		Banner banner = new Banner();
		
		if(bannerId==null || bannerId.equals("")) {
			rsInfo.setMessage("请输入bannerID");
		    rsInfo.setCode("-1");
		    return rsInfo;
		}else {
			banner.setBannerId(Integer.valueOf(bannerId));
		}

		if (bannerType != null && !bannerType.equals("")) {
			banner.setBannerType(Integer.valueOf(bannerType));
		}
		if(jumpUrl!=null||!"".equals(jumpUrl)) {
	        banner.setJumpUrl(jumpUrl);
		}
		if (location != null && !location.equals("")) {
			banner.setLocation(Integer.valueOf(location));
		}
		if(activated!=null||!"".equals(activated)) {
	        banner.setActivated(Integer.valueOf(activated));
		}
		if(locationUrl!=null||!"".equals(locationUrl)) {
	        banner.setLocationUrl(locationUrl);
		}

		// 处理图片
		if (bannerImage != null) {
				String bannerImageFile = System.currentTimeMillis() + bannerImage.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("banner", bannerImageFile, bannerImage);
					NewsImage image = new NewsImage();
					image.setImageUrl(imageUrl);
					image.setCreatedDate(new Date());
				} catch (Exception e) {
					e.printStackTrace();
					rsInfo.setCode("-1");
					rsInfo.setMessage("上传新闻图片失败");
					return rsInfo;	
			}
				
				banner.setImageUrl("1111111111");
			}
		
		banner.setUpdatedDate(new Date());
		
		try {
			Object object = bannerService.modifyBanner(banner);
			System.out.println(object);
			return object;
		} catch (Exception e) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("修改异常");
			return rsInfo;
		}
	}

	/**
	 * Description: 删除广告
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/banner/deleteBanner")
	public @ResponseBody Object deleteBanner(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		String bannerId = request.getParameter("bannerId");
		
		if (bannerId == null || bannerId.equals("")) {
			rsInfo.setMessage("广告Id不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		int count = 0;
		try {
			count = bannerService.delBanner(Integer.parseInt(bannerId));
		} catch (Exception e) {
			e.printStackTrace();
			rsInfo.setMessage("删除广告异常!");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (count > 0) {
			rsInfo.setMessage("删除广告成功！");
			rsInfo.setResult(count);
		} else {
			rsInfo.setMessage("删除广告失败！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		return rsInfo;
	}

	/**
	 * Description: 查看广告详情
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/banner/getBannerById")
	public @ResponseBody Object getBannerById(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		String bannerId = request.getParameter("bannerId");
		
		if (bannerId == null || bannerId.equals("")) {
			rsInfo.setMessage("广告ID不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		Banner banner = null;
		try {
			banner = bannerService.getBannerByBannerId(Integer
					.parseInt(bannerId));
		} catch (Exception e) {
			e.printStackTrace();
			rsInfo.setMessage("获取广告信息失败！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (banner == null) {
			rsInfo.setMessage("广告信息不存在！");
			rsInfo.setResult(new Banner());
			rsInfo.setTotal(0);
			return rsInfo;
		}
		rsInfo.setResult(banner);
		rsInfo.setMessage("获取广告成功！");
		return rsInfo;
	}

	/**
	 * @description 获取广告列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/banner/getBannerList")
	public @ResponseBody Object getBannerList(HttpServletRequest request,
			HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		HashMap<String, Object> params = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String bannerId = request.getParameter("banner_id");

		if (pageNum == null || pageNum.equals("")) {
			rsInfo.setMessage("页码不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (pageSize == null || pageSize.equals("")) {
			rsInfo.setMessage("页大小不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (bannerId != null && !bannerId.equals("")) {
			params.put("bannerId", bannerId);
		}

		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = bannerService.countTotal(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取广告总数异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (totalCount == 0) {
			rsInfo.setMessage("广告数量为零");
			rsInfo.setResult(null);
			return rsInfo;
		}

		List<Banner> bannerList = null;
		try {
			bannerList = bannerService.getBannerList(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取广告列表异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (bannerList.size() == 0) {
			rsInfo.setMessage("广告列表为空！");
			rsInfo.setResult(new ArrayList<Banner>());
			return rsInfo;
		}
		rsInfo.setResult(bannerList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取广告列表成功！");
		return rsInfo;
	}

	/**
	 * 批量删除广告
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/banner/deleteBannerBatch")
	public @ResponseBody Object deleteBatch(HttpServletRequest request,
			HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		String[] idArr = request.getParameterValues("id");

		if (idArr == null || idArr.length < 1) {
			rsInfo.setMessage("ID不能为空！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		int count = 0;
		try {
			count = bannerService.deleteBatch(idArr);
		} catch (Exception e) {
			e.printStackTrace();
			rsInfo.setMessage("批量删除异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (count > 0) {
			rsInfo.setMessage("批量删除成功！");
			rsInfo.setResult(count);
		} else {
			rsInfo.setMessage("批量删除失败！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		return rsInfo;
	}
}
