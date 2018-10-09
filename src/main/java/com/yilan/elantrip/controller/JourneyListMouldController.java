package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.JourneyListMould;
import com.yilan.elantrip.service.JourneyListMouldService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 行程单模板的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class JourneyListMouldController {

	@Resource
	private JourneyListMouldService journeyListMouldService; // 注入

	/**
	 * 添加行程单模板
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/journeyListMould/addJourneyListMould")
	public Object addJourneyListMould(String type, String content) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		JourneyListMould journeyListMould = new JourneyListMould();
		// 校验参数
		if (StringUtils.isEmpty(type)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("类型不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(content)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("内容不能为空");
			return resultInfo;
		}
		// 封装数据
		journeyListMould.setType(Integer.parseInt(type));
		journeyListMould.setContent(content);
		journeyListMould.setCreatedDate(new Date());
		// 调用service层的方法
		try {
			Object object = journeyListMouldService.addJourneyListMould(journeyListMould);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加行程单模板异常");
			return resultInfo;
		}
	}

	/**
	 * 获取行程单模板详情
	 * 
	 * @param journeyListMouldId
	 *            行程单模板Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/journeyListMould/getJourneyListMould")
	public Object getJourneyListMould(String journeyListMouldId) {
		ResultInfo resultInfo = new ResultInfo();
		// 校验参数
		if (StringUtils.isEmpty(journeyListMouldId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("行程单模板Id不能为空");
			return resultInfo;
		}
		// 调用service层的方法
		try {
			Object object = journeyListMouldService.getJourneyListMouldById(Integer.parseInt(journeyListMouldId));
			return object;
		} catch (Exception e) {
			System.out.println(e);
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 修改行程单模板
	 * 
	 * @param journeyListMouldId
	 *            行程单模板Id
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/journeyListMould/modifyJourneyListMould")
	public Object modifyJourneyListMould(String journeyListMouldId, String type, String content) {
		ResultInfo resultInfo = new ResultInfo();
		JourneyListMould journeyListMould = new JourneyListMould();
		// 校验参数
		if (StringUtils.isEmpty(journeyListMouldId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("行程单模板Id不能为空");
			return resultInfo;
		}
		journeyListMould.setJourneyListMouldId(Integer.parseInt(journeyListMouldId));
		if (!StringUtils.isEmpty(type)) {
			journeyListMould.setType(Integer.parseInt(type));
		}

		if (!StringUtils.isEmpty(content)) {
			journeyListMould.setContent(content);
		}
		journeyListMould.setUpdatedDate(new Date());
		// 调用service层的方法
		try {
			Object object = journeyListMouldService.updateJourneyListMould(journeyListMould);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改异常");
			return resultInfo;
		}
	}

	/**
	 * 删除行程单模板
	 * 
	 * @param journeyListMouldId
	 *            行程单模板Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/journeyListMould/delJourneyListMould")
	public Object delJourneyListMould(String journeyListMouldId) {
		ResultInfo resultInfo = new ResultInfo();
		// 校验参数
		if (StringUtils.isEmpty(journeyListMouldId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("行程单模板Id不能为空");
			return resultInfo;
		}
		// 调用service层的方法
		try {
			Object object = journeyListMouldService.DelJourneyListMouldById(Integer.parseInt(journeyListMouldId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除异常");
			return resultInfo;
		}
	}

	/**
	 * 获取行程单模板列表
	 * 
	 * @param type
	 *            类型
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/journeyListMould/getJourneyListMouldList")
	public @ResponseBody Object getJourneyListMouldList(String type,
			HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (pageSize == null || pageSize.equals("")) {
			reInfo.setMessage("页大小不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!StringUtils.isEmpty(type)) {
			params.put("type", type);
		}
		if (pageNum == null || pageNum.equals("")) {
			reInfo.setMessage("页码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = (int) journeyListMouldService.countTotal(pageTool);
		} catch (Exception e) {
			System.out.println(e);
			reInfo.setMessage("获取总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}

		List<JourneyListMould> journeyListMouldList = null;
		try {
			journeyListMouldList = journeyListMouldService.getJourneyListMouldList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (journeyListMouldList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<JourneyListMould>());
			return reInfo;
		}
		reInfo.setResult(journeyListMouldList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}
