package com.yilan.elantrip.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Setting;
import com.yilan.elantrip.service.SettingService;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 配置的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class SettingController {

	@Resource
	private SettingService settingService; // 注入

	/**
	 * 获取配置详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/setting/getSetting")
	public Object getUser(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		Integer settingId = 1;
		// 调用service层的方法
		try {
			Object object = settingService.getSetting(settingId);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加配置
	 * @param aboutUs 关于我们
	 * @param copyRight 版权声明
	 * @param mobile 联系电话
	 * @param address 联系地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/setting/addSetting")
	public Object addUser(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String aboutUs = request.getParameter("aboutUs");
		String copyRight = request.getParameter("copyRight");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		// 校验参数
		if (StringUtils.isEmpty(aboutUs)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("关于我们不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(copyRight)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("版权声明不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(address)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("地址不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(mobile)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("手机号码不能为空");
			return resultInfo;
		}
		// 封装数据
		Setting setting = new Setting();
		setting.setAboutUs(aboutUs);
		setting.setAddress(address);
		setting.setMobile(mobile);
		setting.setCopyRight(copyRight);
		setting.setCreatedDate(new Date());
		// 调用service层的方法
		Object object = null;
//		try {
//			object = settingService.getSetting(1);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			resultInfo.setCode("-1");
//			resultInfo.setMessage("查找异常");
//			return resultInfo;
//		}
		try {
			object = settingService.addSetting(setting);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加异常");
			return resultInfo;
		}
	}

	/**
	 * 修改配置
	 * @param aboutUs 关于我们
	 * @param copyRight 版权声明
	 * @param mobile 联系电话
	 * @param address 联系地址
	 * @param request
	 * @return
	 */
	@RequestMapping("/setting/modifySetting")
	public Object modifyUser(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		Integer settingId = 1;
		String aboutUs = request.getParameter("aboutUs");
		String copyRight = request.getParameter("copyRight");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		Setting setting = new Setting();
		setting.setSettingId(settingId);

		setting.setUpdatedDate(new Date());
		// 校验参数
		if (!StringUtils.isEmpty(aboutUs)) {
			setting.setAboutUs(aboutUs);
		}
		if (!StringUtils.isEmpty(copyRight)) {
			setting.setCopyRight(copyRight);
		}
		if (!StringUtils.isEmpty(address)) {
			setting.setAddress(address);
		}
		if (!StringUtils.isEmpty(mobile)) {
			setting.setMobile(mobile);
		}

		// 调用service层的方法
		try {
			Object object = settingService.modifySetting(setting);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改异常");
			return resultInfo;
		}
	}
}
