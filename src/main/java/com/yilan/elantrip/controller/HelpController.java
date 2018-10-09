package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.domain.HelpImage;
import com.yilan.elantrip.service.HelpService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@RestController // 直接使用@RestController，不用@ResponseBody
public class HelpController {

	@Resource
	private HelpService helpService; // 注入
	
	/**
	 * 获取问题详情
	 * 
	 * @param helpId
	 *            问题编号
	 * @param request
	 * @return
	 */
	@RequestMapping("/help/getHelp")
	public Object getHelp(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String type = request.getParameter("type"); // 根据type获取问题

		// 校验参数
		if (StringUtils.isEmpty(type)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("问题类型不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = helpService.getHelp(Integer.parseInt(type));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}
	
	/**
	 * 获取问题列表
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/help/getHelpList")
	@ResponseBody Object getHelpList(HttpServletRequest request,
			HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		HashMap<String, Object> params = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
//		String type = request.getParameter("type");
		String helpId = request.getParameter("helpId");

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
		params.put("pageNum",pageNum);
		params.put("pageSize",pageSize);
//		if (pageSize == null || pageSize.equals("")) {
//			rsInfo.setMessage("请选择问题类别");
//			rsInfo.setCode("-1");
//			return rsInfo;
//		}
//		else {
//			params.put("type", type);
//		}

		if (helpId != null && !helpId.equals("")) {
			params.put("helpId", helpId);
		
		}
       
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		 System.out.println(params.get("pageNum"));
		 System.out.println(params.get("pageSize"));
//		 System.out.println(type);

		try {
			totalCount = helpService.countTotal(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取问题总数异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}

		if (totalCount == 0) {
			rsInfo.setMessage("问题数量为零");
			rsInfo.setResult(null);
			return rsInfo;
		}

		List<Help> helpList = null;
		try {
			helpList = helpService.getHelpList(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取问题列表异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (helpList.size() == 0) {
			rsInfo.setMessage("问题列表为空！");
			rsInfo.setResult(new ArrayList<Help>());
			return rsInfo;
		}
		rsInfo.setResult(helpList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取问题列表成功！");
		return rsInfo;
	}


	/**
	 * 添加问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/help/modifyHelp")
	public Object modifyHelp(
			//url指答案上传的图片地址
			@RequestParam(value = "url", required = false) MultipartFile[] answerImage,
			String question,
			String answer
			){
		ResultInfo rsInfo=new ResultInfo();  //封装结果集 
		
		//参数校验
		if (StringUtils.isEmpty(question)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("问题不能为空");
			return rsInfo;
		}
		if (StringUtils.isEmpty(answer)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("答案不能为空");
			return rsInfo;
		}

		        //封装数据
				Help help = new Help();
				help.setAnswer(answer);
				help.setQuestion(question);
				//help.setHelpId(helpId);
				//help.setCreatedDate(new Date());

				List<HelpImage> asImages = new ArrayList<HelpImage>();
                //多张图片上传
//				for (MultipartFile image : answerImage) {
//					String images = System.currentTimeMillis()
//							+ image.getOriginalFilename();
//					String imageUrl = null;
//					try {
//						imageUrl = UploadFileUtils.uploadFile("image", images,
//								image);
//						HelpImage helpImage = new HelpImage();
//						helpImage.setUrl(imageUrl);
//						asImages.add(helpImage);
//					} catch (Exception e) {
//						e.printStackTrace();
//						rsInfo.setCode("-1");
//						rsInfo.setMessage("上传图片失败，请重新上传");
//						return rsInfo;
//					}
//				}
				HelpImage hImage1 = new HelpImage();
				HelpImage hImage2 = new HelpImage();
				hImage1.setUrl("111");
				hImage2.setUrl("2222");
				
				

		//调用service层的方法
		try {
			Object object=helpService.addHelp(help, asImages);
			return object;
		} catch (Exception e) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("添加问题异常");
			return rsInfo;
		}
		
	}
	/**
	 * 删除帮助问题
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/help/delHelp")
	public Object delHelp(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String helpId = request.getParameter("help_Id"); // 用户Id

		// 校验参数
		if (StringUtils.isEmpty(helpId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("问题编号不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = helpService.deleteHelp(Integer.valueOf(helpId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除异常");
			return resultInfo;
		}
	}
	
}
