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
import com.yilan.elantrip.domain.Channel;
import com.yilan.elantrip.service.ChannelService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 渠道的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class ChannelController {

	@Resource
	private ChannelService channelService; // 注入

	/**
	 * 获取渠道详情
	 * 
	 * @param channelId
	 *            渠道Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/channel/getChannel")
	public Object getChannel(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String channelId = request.getParameter("channelId");

		// 校验参数
		if (StringUtils.isEmpty(channelId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("渠道Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = channelService.getChannel(Integer.parseInt(channelId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加渠道
	 * 
	 * @param name
	 *            渠道名称
	 * @param type
	 *            渠道类型：1 内部 2 企业 3 同行 4 投放上游5  投放下游
	 * @param num
	 *            渠道号
	 * @param contactPerson
	 *            联系人
	 * @param contactMobile
	 *            联系电话
	 * @param remark
	 *            备注
	 * @param activated
	 *            是否有效
	 * @return
	 */
	@RequestMapping("/channel/addChannel")
	public Object addChannel(String name, String type, String num, String contactPerson, String contactMobile,
			String remark, String activated) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 校验参数
		if (StringUtils.isEmpty(name)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("渠道名称不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(type)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("渠道类型不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(num)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("渠道号不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(contactPerson)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("联系人不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(contactMobile)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("联系电话不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(remark)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("备注不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(activated)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("状态不能为空");
			return resultInfo;
		}

		// 封装数据
		Channel channel = new Channel();
		channel.setContactMobile(contactMobile);
		channel.setActivated(Integer.parseInt(activated));
		channel.setCreatedDate(new Date());
		channel.setContactPerson(contactPerson);
		channel.setNum(num);
		channel.setType(Integer.parseInt(type));
		channel.setName(name);
		channel.setRemark(remark);
		// 调用service层的方法
		try {
			Object object = channelService.addChannel(channel);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加渠道异常");
			return resultInfo;
		}
	}

	/**
	 * 修改渠道
	 * 
	 * @param channelId
	 *            渠道ID
	 * @param name
	 *            渠道名称
	 * @param type
	 *            类型
	 * @param num
	 *            渠道号
	 * @param contactPerson
	 *            联系人
	 * @param contactMobile
	 *            联系电话
	 * @param remark
	 *            备注
	 * @param activated
	 *            是否有效
	 * @return
	 */
	@RequestMapping("/channel/modifyChannel")
	public Object modify(String channelId, String name, String type, String num, String contactPerson,
			String contactMobile, String remark, String activated) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		Channel channel = new Channel();
		// 校验参数
		if (StringUtils.isEmpty(channelId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("渠道ID不能为空");
			return resultInfo;
		}
		channel.setChannelId(Integer.parseInt(channelId));

		if (!StringUtils.isEmpty(name)) {
			channel.setName(name);
		}
		if (!StringUtils.isEmpty(type)) {
			channel.setType(Integer.parseInt(type));
		}
		if (!StringUtils.isEmpty(num)) {
			channel.setNum(num);
		}
		if (!StringUtils.isEmpty(contactPerson)) {
			channel.setContactPerson(contactPerson);
		}
		if (!StringUtils.isEmpty(contactMobile)) {
			channel.setContactMobile(contactMobile);
		}
		if (!StringUtils.isEmpty(remark)) {
			channel.setRemark(remark);
		}
		if (!StringUtils.isEmpty(activated)) {
			channel.setActivated(Integer.parseInt(activated));
		}
		channel.setUpdatedDate(new Date());
		// 调用service层的方法
		try {
			Object object = channelService.modifyChannel(channel);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改渠道异常");
			return resultInfo;
		}
	}

	/**
	 * 获取渠道列表
	 * 
	 * @param type
	 *            类型
	 * @param num
	 *            渠道号
	 * @param contactPerson
	 *            联系人
	 * @param contactMobile
	 *            联系电话
	 * @param remark
	 *            备注
	 * @param activated
	 *            是否有效
	 * @param pageNum
	 *            起始页
	 * @param pageSize
	 *            每页显示数量
	 * @return
	 */
	@RequestMapping(value = "/channel/getChannelList")
	public @ResponseBody Object getNewsList(HttpServletRequest request, String type, String num, String contactPerson,
			String contactMobile, String activated) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
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
		if (!StringUtils.isEmpty(type)) {
			params.put("type", type);
		}
		if (!StringUtils.isEmpty(num)) {
			params.put("num", num);
		}
		if (!StringUtils.isEmpty(contactPerson)) {
			params.put("contactPerson", contactPerson);
		}
		if (!StringUtils.isEmpty(contactMobile)) {
			params.put("contactMobile", contactMobile);
		}
		if (!StringUtils.isEmpty(activated)) {
			params.put("activated", activated);
		}

		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) channelService.countTotal(pageTool);
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

		List<Channel> channelList = null;
		try {
			channelList = channelService.getChannelList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (channelList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Channel>());
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setResult(channelList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}
