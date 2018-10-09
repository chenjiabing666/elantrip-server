package com.yilan.elantrip.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.util.SendSmsUtil;
import com.yilan.elantrip.util.SendMailUtils;
import com.yilan.elantrip.util.CRCode;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.StringUtil;
import com.yilan.elantrip.util.UploadFileUtils;
import com.yilan.elantrip.domain.User;
import com.yilan.elantrip.domain.UserLinkTravller;
import com.yilan.elantrip.service.UserService;
import com.yilan.elantrip.util.ResultInfo;

/**
 * 用户的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class UserController {

	@Resource
	private UserService userService; // 注入

	/***
	 * 同行/企业开户
	 * 
	 * @param request
	 * @param userName
	 *            企业用户账号名
	 * @param companyLevel
	 *            企业用户等级
	 * @param companyName
	 *            公司名称
	 * @param country
	 *            国家
	 * @param province
	 *            省份
	 * @param city
	 *            市
	 * @param legal
	 *            法人
	 * @param contactName
	 *            联系人
	 * @param email
	 *            邮箱
	 * @param mobile
	 *            电话
	 * @param contract
	 *            合同
	 * @param remark
	 *            备注
	 * @return
	 */
	@RequestMapping("/user/businessOpening")
	public Object regist(HttpServletRequest request, String userName, String companyLevel, String companyName,
			String country, String province, String city, String legal, String contactName, String email, String mobile,
			MultipartFile contract, String remark) {
		ResultInfo resultInfo = new ResultInfo();
		if (StringUtils.isEmpty(userName)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("企业用户账号名不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(companyLevel)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("企业用户等级不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(companyName)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("公司名称不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(country)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("国家不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(province)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("省份不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(city)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("市不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(legal)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("法人不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(contactName)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("联系人不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(email)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("邮箱不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("电话不能为空");
			return resultInfo;
		}
		if (contract == null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("合同不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(remark)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("备注不能为空");
			return resultInfo;
		}
		User user = new User();
		user.setUserName(userName);
		user.setCompanyLevel(Integer.parseInt(companyLevel));
		user.setCompanyName(companyName);
		user.setCountry(country);
		user.setProvince(province);
		user.setCity(city);
		user.setLegal(legal);
		user.setContactName(contactName);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setRemark(remark);
		String imageName = System.currentTimeMillis() + contract.getOriginalFilename();
		String imageUrl = null;
		try {
			imageUrl = UploadFileUtils.uploadFile("icon", imageName, contract);
			user.setContract(imageUrl);
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("上传合同失败");
			return resultInfo;
		}
		user.setPassword(mobile.substring(mobile.length() - 6));
		try {
			Object object = userService.addUser(user);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("注册异常");
			return resultInfo;
		}
	}

	/**
	 * 个人用户注册 手机注册
	 * 
	 * @param mobile
	 *            手机号码
	 * @param password
	 *            密码
	 * @param invitationCode
	 *            推荐码
	 * @param code
	 *            验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/registByMobile")
	public Object regist(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String invitationCode = request.getParameter("invitationCode");
		String code = request.getParameter("code");
		// 校验参数
		if (StringUtils.isEmpty(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("手机号码不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(password)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("密码不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(code)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("验证码不能为空");
			return resultInfo;
		}
		if (!StringUtil.isMobile(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("手机号码格式错误");
			return resultInfo;
		}
		if (password.length() < 6) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("密码至少六位");
			return resultInfo;
		}
		User user = null;
		try {
			user = userService.getUserByMobile(mobile);
		} catch (Exception e1) {
			resultInfo.setMessage("获取用户异常");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		if (user != null) {
			resultInfo.setMessage("用户已存在！");
			resultInfo.setCode("-1");
			return resultInfo;

		}

		// 验证验证码，时长一分钟
		CRCode crCode = (CRCode) request.getSession().getAttribute("code");

		if (crCode == null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("请先获取验证码");
			return resultInfo;
		}

		// 获取了验证码

		// 手机号码不同
		if (!crCode.getMobile().equals(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("该手机号码没有获取验证码，请先获取验证码");
			return resultInfo;
		}

		// 验证一分钟
		Long sendTime = crCode.getSendDate().getTime();
		// 如果时间大于一分钟
		if (((System.currentTimeMillis() - sendTime) / 1000 / 60) > 1) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("验证码失效，请重新发送");
			return resultInfo;
		}

		// 验证码是否相同

		if (!code.equals(crCode.getCrCode())) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("验证码错误，请重新输入");
			return resultInfo;
		}

		// 验证码完成验证，那么可以直接注册了
		user = new User();
		user.setMobile(mobile);
		user.setPassword(password);
		user.setTmpType("2,4");
		user.setCreatedDate(new Date());
		if (!StringUtils.isEmpty(invitationCode)) {
			user.setInvitationCode(invitationCode);
		}
		try {
			Object object = userService.addUser(user);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("注册异常");
			return resultInfo;
		}
	}

	/**
	 * 获取短信的验证码
	 * 
	 * @param mobile
	 *            手机号码
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getCode")
	public Object getCode(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String mobile = request.getParameter("mobile");
		// 校验参数
		mobile = "13648050230";
		if (StringUtils.isEmpty(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("手机号码不能为空");
			return resultInfo;
		}

		if (!StringUtil.isMobile(mobile)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("手机号码格式错误");
			return resultInfo;
		}

		String code = SendSmsUtil.getSixRadam(); // 获取六位随机的验证码

		// 验证码限制，一个小时十条
		CRCode crCode = (CRCode) request.getSession().getAttribute("code");

		// 如果session中没有信息，那么可以直接发送
		if (crCode == null) {
			try {
				System.out.println("$$$$");
				SendSmsUtil.sendUserCrCode(mobile, code); // 发送
				crCode = new CRCode();
				crCode.setMobile(mobile);
				crCode.setCrCode(code);
				crCode.setNumber(1);
				crCode.setSendDate(new Date());
				request.getSession().setAttribute("code", crCode);
				resultInfo.setMessage("发送成功");
				return resultInfo;
			} catch (Exception e) {
				resultInfo.setMessage("验证码发送异常");
				resultInfo.setCode("-1");
				return resultInfo;
			}
		}

		// 如果session已经存在了，需要判断一个小时之内的条数

		// 判断是否间隔了一个小时了

		Long sendTime = crCode.getSendDate().getTime();

		// 如果大于一个小时了，那么可以直接发送了
		if (((System.currentTimeMillis() - sendTime) / 1000 / 3600) > 1) {
			try {
				SendSmsUtil.sendCodeByModifyPassword(mobile, code); // 发送
				crCode.setMobile(mobile);
				crCode.setCrCode(code);
				crCode.setNumber(1); // 短信的条数恢复 成一条
				crCode.setSendDate(new Date());
				request.getSession().setAttribute("code", crCode);
				resultInfo.setMessage("发送成功");
				return resultInfo;
			} catch (Exception e) {
				resultInfo.setMessage("验证码发送异常");
				resultInfo.setCode("-1");
				return resultInfo;
			}
		} else { // 如果没有超过一个小时，那么验证短信的条数

			if (crCode.getNumber() > 10) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("一个小时之内只能发送10条");
				return resultInfo;
			} else {
				try {
					SendSmsUtil.sendCodeByModifyPassword(mobile, code); // 发送
					crCode.setMobile(mobile);
					crCode.setCrCode(code);
					crCode.setNumber(crCode.getNumber() + 1); // 短信的条数恢复 成一条
					crCode.setSendDate(new Date());
					request.getSession().setAttribute("code", crCode);
					resultInfo.setCode("0");
					resultInfo.setMessage("发送成功");
					return resultInfo;
				} catch (Exception e) {
					resultInfo.setMessage("验证码发送异常");
					resultInfo.setCode("-1");
					return resultInfo;
				}
			}

		}

	}

	/**
	 * 个人用户邮箱注册
	 * 
	 * @param email
	 *            邮箱
	 * @param password
	 *            密码
	 * @param invitationCode
	 *            推荐码
	 * @return user
	 * 
	 */
	@RequestMapping(value = "/user/registByEmail")
	public @ResponseBody Object register(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo();
		String password = request.getParameter("password");// 密码
		String email = request.getParameter("email");// 邮箱
		String invitationCode = request.getParameter("invitationCode");
		if (password == null || password.equals("")) {
			reInfo.setMessage("密码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (email == null || email.equals("")) {
			reInfo.setMessage("邮箱不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		User user = null;
		try {
			user = userService.getUserByEmail(email);
		} catch (Exception e1) {
			reInfo.setMessage("获取用户异常");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (user != null) {
			reInfo.setMessage("用户已存在！");
			reInfo.setCode("-1");
			return reInfo;
		} else {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setTmpType("2,4");
			user.setActivated(2);
			user.setCreatedDate(new Date());
			if (!StringUtils.isEmpty(invitationCode)) {
				user.setInvitationCode(invitationCode);
			}
			Map<String, String> info = new HashMap<String, String>();
			try {
				Object object = userService.addUser(user);
				if (object != null) {
					info.put("subject", "注册邮件");
					info.put("receiveMail", user.getEmail());
					// info.put("content", "尊敬的"
					// + "先生/女士<br/>您好!<br/>欢迎您注册意蓝旅游,请收到邮件后点击下面的链接进行激活账号</br><h3><a
					// href='http://www.emoonbow.com:8081/elantrip-server/user/modifyActivated.do?userId="
					// + user.getUserId()
					// +
					// "'>http://www.emoonbow.com:8081/medichome-server/user/modifyActivated?userId="
					// + user.getUserId() + "</a></h3>");
					// 测试
					info.put("content", "尊敬的"
							+ "先生/女士<br/>您好!<br/>欢迎您注册意蓝旅游,请收到邮件后点击下面的链接进行激活账号</br><h3><a href='http://localhost:8080/elantrip-server/user/modifyActivated?email="
							+ user.getEmail() + "'>http://localhost:8080/medichome-server/user/modifyActivated?userId="
							+ user.getEmail() + "</a></h3>");

					SendMailUtils.sendHtmlEmail(info);
					reInfo.setCode("0");
					reInfo.setMessage("注册成功！");
				} else {
					reInfo.setMessage("注册失败！");
					reInfo.setCode("-1");
					return reInfo;
				}
			} catch (Exception e) {
				reInfo.setMessage("注册失败！");
				reInfo.setCode("-1");
				return reInfo;
			}

		}

		reInfo.setResult(user);
		return reInfo;
	}

	/**
	 * 邮箱激活用户
	 * 
	 * @param email
	 *            邮箱
	 * 
	 */
	@RequestMapping(value = "/user/modifyActivated")
	public @ResponseBody Object modifyActivated(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, String email) {
		ResultInfo reInfo = new ResultInfo();
		if (email == null || email.equals("")) {
			reInfo.setMessage("邮箱不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		int count = 0;
		try {
			count = (int) userService.activateUser(email);
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
		reInfo.setMessage("修改信息成功！");
		reInfo.setCode("0");
		return reInfo;
	}

	/**
	 * 个人用户二维码注册
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param invitationCode
	 *            推荐码
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/registByQRCode")
	public Object QRCoderegiste(String userName, String password, String invitationCode, HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		// 校验参数
		if (StringUtils.isEmpty(userName)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("用户名不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(password)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("密码不能为空");
			return resultInfo;
		}
		User user = null;
		try {
			user = userService.getUserByUserName(userName);
		} catch (Exception e1) {
			resultInfo.setMessage("获取用户异常");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		if (user != null) {
			resultInfo.setMessage("用户已存在！");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setTmpType("2,4");
		Object object = null;
		try {
			object = userService.addUser(user);
			return object;
		} catch (Exception e) {
			resultInfo.setMessage("注册失败！");
			resultInfo.setCode("-1");
			return resultInfo;
		}
	}

	/**
	 * 普通登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/login")
	public Object login(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		HashMap<String, String> params = new HashMap<String, String>();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(userName)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("用户名不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(password)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("密码不能为空");
			return resultInfo;
		}
		// 判断是邮箱还是手机号的正则表达式
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String ph = "^[1][34578]\\d{9}$";
		if (userName.matches(em)) {
			params.put("email", userName);
		} else if (userName.matches(ph)) {
			params.put("mobile", userName);
		} else {
			params.put("userName", userName);
		}
		params.put("password", password);
		Object object = null;
		try {
			object = userService.login(params);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("登录异常");
			return resultInfo;
		}
	}

	/***
	 * 手机动态码登录
	 * 
	 * @param request
	 * @param mobile
	 *            手机号
	 * @param authcode
	 *            验证码
	 * @return
	 */
	@RequestMapping("/user/loginByDynamicCode")
	public Object loginByDynamicCode(HttpServletRequest request, String mobile, String authcode, HttpSession session) {
		ResultInfo reInfo = new ResultInfo();
		if (mobile == null || mobile.equals("")) {
			reInfo.setMessage("手机号不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setMessage("验证码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		User user = null;
		try {
			user = userService.getUserByMobile(mobile);
		} catch (Exception e1) {
			reInfo.setCode("-1");
			reInfo.setMessage("获取用户异常");
			return reInfo;
		}
		if (user != null) {
			reInfo.setMessage("改手机号未注册或者未绑定");
			reInfo.setCode("-1");
			return reInfo;
		}
		CRCode codes = (CRCode) session.getAttribute("code");
		if (codes == null) {
			reInfo.setMessage("请先获取验证码！");
			reInfo.setCode("-1");
			return reInfo;
		}
		long nowtime = System.currentTimeMillis();
		long pasttime = codes.getSendDate().getTime();

		if ((nowtime - pasttime) > 300000) {
			reInfo.setMessage("验证码已过期");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!authcode.equals(codes.getCrCode())) {
			reInfo.setCode("-1");
			reInfo.setMessage("验证码错误！");
			return reInfo;
		}
		try {
			reInfo.setResult(userService.getUserByMobile(mobile));
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("获取用户异常");
			return reInfo;
		}
		reInfo.setMessage("登录成功");
		reInfo.setCode("0");
		return reInfo;
	}

	/**
	 * Description: 验证手机
	 * 
	 * @param userId
	 *            用户ID
	 * @param mobile
	 *            手机号
	 * @param authcode
	 *            验证码
	 * 
	 * @return count
	 * 
	 */
	@RequestMapping(value = "/user/bindMobile")
	public @ResponseBody Object bindMobile(HttpServletRequest request, HttpSession session) {

		ResultInfo reInfo = new ResultInfo();

		String userId = request.getParameter("userId");
		String mobile = request.getParameter("mobile");
		String authcode = request.getParameter("authcode");
		if (userId == null || userId.equals("")) {
			reInfo.setMessage("用户ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (mobile == null || mobile.equals("")) {
			reInfo.setMessage("手机号不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setMessage("验证码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		User user = null;
		try {
			user = userService.getUserByMobile(mobile);
		} catch (Exception e1) {
			reInfo.setMessage("获取用户异常");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (user != null) {
			reInfo.setMessage("手机已绑定！");
			reInfo.setCode("-1");
			return reInfo;

		}
		CRCode codes = (CRCode) session.getAttribute("code");
		if (codes == null) {
			reInfo.setMessage("请先获取验证码！");
			reInfo.setCode("-1");
			return reInfo;
		}
		long nowtime = System.currentTimeMillis();
		long pasttime = codes.getSendDate().getTime();

		if ((nowtime - pasttime) > 300000) {
			reInfo.setMessage("验证码已过期");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!authcode.equals(codes.getCrCode())) {
			reInfo.setCode("-1");
			reInfo.setMessage("验证码错误！");
			return reInfo;
		}
		user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setMobile(mobile);
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			reInfo.setMessage("修改用户手机出错！");
			reInfo.setCode("-1");
			return reInfo;
		}

		return reInfo;
	}

	/***
	 * 修改密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	@RequestMapping("/user/modifyPassword")
	public Object modifyPassword(String userId, String oldPassword, String newPassword) {
		ResultInfo resultInfo = new ResultInfo();
		String password = null;
		if (StringUtils.isEmpty(userId)) {
			resultInfo.setMessage("用户ID不能为空");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		if (StringUtils.isEmpty(oldPassword)) {
			resultInfo.setMessage("旧密码不能为空");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		if (StringUtils.isEmpty(newPassword)) {
			resultInfo.setMessage("新密码不能为空");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		try {
			User user = (User) userService.getUser(Integer.parseInt(userId));
			password = user.getPassword();
		} catch (Exception e) {
			System.out.println(e);
			resultInfo.setMessage("查找用户密码出错！");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		if (password.equals(oldPassword)) {
			User user = new User();
			user.setUserId(Integer.parseInt(userId));
			user.setPassword(newPassword);
			try {
				userService.modifyUser(user);
				resultInfo.setMessage("修改密码成功");
				resultInfo.setCode("0");
				return resultInfo;
			} catch (Exception e) {
				resultInfo.setMessage("修改密码失败");
				resultInfo.setCode("-1");
				return resultInfo;
			}
		} else {
			resultInfo.setMessage("旧密码不正确");
			resultInfo.setCode("-1");
			return resultInfo;
		}
	}

	/***
	 * 忘记密码
	 * 
	 * @param userId
	 *            用户id
	 * @param mobile
	 *            手机号
	 * @param newPassword
	 *            新密码
	 * @param authcode
	 *            验证码
	 * @return
	 */
	@RequestMapping("/user/forgetPassword")
	public Object forgetPassword(String userId, String mobile, String newPassword, String authcode,
			HttpSession session) {
		ResultInfo reInfo = new ResultInfo();
		if (userId == null || userId.equals("")) {
			reInfo.setMessage("用户ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (mobile == null || mobile.equals("")) {
			reInfo.setMessage("手机号不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setMessage("验证码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (authcode == null || authcode.equals("")) {
			reInfo.setMessage("新密码不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		User user = null;
		CRCode codes = (CRCode) session.getAttribute("code");
		if (codes == null) {
			reInfo.setMessage("请先获取验证码！");
			reInfo.setCode("-1");
			return reInfo;
		}
		long nowtime = System.currentTimeMillis();
		long pasttime = codes.getSendDate().getTime();

		if ((nowtime - pasttime) > 300000) {
			reInfo.setMessage("验证码已过期");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (!authcode.equals(codes.getCrCode())) {
			reInfo.setCode("-1");
			reInfo.setMessage("验证码错误！");
			return reInfo;
		}
		user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setPassword(newPassword);
		try {
			userService.modifyUser(user);
			return reInfo;
		} catch (Exception e) {
			reInfo.setMessage("修改密码失败");
			reInfo.setCode("-1");
			return reInfo;
		}

	}

	/**
	 * 添加用户联系人或者出游人
	 * 
	 * @param userId
	 *            用户id
	 * @param userLinkTravllers
	 *            联系人或出游人
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/addUserLinkTravller")
	public Object addUserLinkTravller(String userLinkTravllers, String userId, HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		UserLinkTravller userLinkTravller = new UserLinkTravller();
		List<UserLinkTravller> userLinkTravllersList = new ArrayList<>();
		userLinkTravllersList.add(userLinkTravller);
		// 调用service层的方法
		try {
			Object object = userService.addUserLinkOrTravller(userLinkTravllersList);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
	}

	/**
	 * 获取用户常用联系人列表
	 * 
	 * @param userId 用户id
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getUserLinkList")
	public Object getUserLinkList(String userId, HttpServletRequest request) {
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
		if (userId == null || userId.equals("")) {
			reInfo.setMessage("用户ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		params.put("userId", userId);
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) userService.userLinkcountTotal(pageTool);
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

		List<UserLinkTravller> userLinkTravllers = null;
		try {
			userLinkTravllers = userService.getUserLinkList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (userLinkTravllers.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<UserLinkTravller>());
			return reInfo;
		}
		reInfo.setResult(userLinkTravllers);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 获取个人用户列表
	 * 
	 * @param userName
	 *            用户名
	 * @param mobile
	 *            手机号
	 * @param activated
	 *            是否失效
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getPersonalUserList")
	public Object getPersonalUserList(HttpServletRequest request, String userName, String mobile, String activated) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> results = new ArrayList<>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		// 校验参数
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
		if (!StringUtils.isEmpty(userName)) {
			params.put("userName", userName);
		}
		if (!StringUtils.isEmpty(mobile)) {
			params.put("mobile", mobile);
		}
		if (!StringUtils.isEmpty(activated)) {
			params.put("activated", activated);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) userService.countTotal(pageTool);
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

		List<User> userList = null;
		try {
			userList = userService.getPersonalUserList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (userList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<User>());
			return reInfo;
		}
		for (User user : userList) {
			LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
			result.put("userId", user.getUserId());
			result.put("userName", user.getUserName());
			result.put("mobile", user.getMobile());
			result.put("gender", user.getGender());
			result.put("type", user.getType());
			result.put("activated", user.getActivated());
			result.put("createdDate", user.getCreatedDate());
			result.put("remark", user.getRemark());
			results.add(result);
		}
		reInfo.setResult(results);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 获取企业用户列表
	 * 
	 * @param companyName
	 *            公司名
	 * @param mobile
	 *            手机号
	 * @param email
	 *            邮箱
	 * @param activated
	 *            是否失效
	 * @param contactName
	 *            联系人
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getCompanyUserList")
	public Object getCompanyUserList(HttpServletRequest request, String companyName, String mobile, String email,
			String activated, String contactName) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> results = new ArrayList<>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		// 校验参数
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
		if (!StringUtils.isEmpty(companyName)) {
			params.put("companyName", companyName);
		}
		if (!StringUtils.isEmpty(mobile)) {
			params.put("mobile", mobile);
		}
		if (!StringUtils.isEmpty(email)) {
			params.put("email", email);
		}
		if (!StringUtils.isEmpty(activated)) {
			params.put("activated", activated);
		}
		if (!StringUtils.isEmpty(contactName)) {
			params.put("contactName", contactName);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = (int) userService.countTotal(pageTool);
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
		List<User> userList = null;
		try {
			userList = userService.getCompanyUserList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (userList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<User>());
			return reInfo;
		}
		for (User user : userList) {
			LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
			result.put("userId", user.getUserId());
			result.put("companyName", user.getCompanyName());
			result.put("email", user.getEmail());
			result.put("contactName", user.getContactName());
			result.put("mobile", user.getMobile());
			result.put("companyLevel", user.getCompanyLevel());
			result.put("activated", user.getActivated());
			result.put("createdDate", user.getCreatedDate());
			result.put("remark", user.getRemark());
			results.add(result);
		}
		reInfo.setResult(results);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 获取用户详情
	 * 
	 * @param userId
	 *            用户Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getUser")
	public Object getUser(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String userId = request.getParameter("userId"); // 用户Id

		// 校验参数
		if (StringUtils.isEmpty(userId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("用户Id不能为空");
			return resultInfo;
		}
		// 调用service层的方法
		try {
			Object object = userService.getUser(Integer.parseInt(userId));
			resultInfo.setResult(object);
			resultInfo.setCode("0");
			resultInfo.setMessage("获取成功");
			return resultInfo;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/***
	 * 修改用户信息
	 * 
	 * @param request
	 * @param userId
	 *            用户id
	 * @param userName
	 *            用户名
	 * @param chinaName
	 *            中文名
	 * @param englishName
	 *            英文名
	 * @param nickName
	 *            昵称
	 * @param icon
	 *            头像
	 * @param birthday
	 *            生日
	 * @param gender
	 *            性别
	 * @param otherMobile
	 *            其他电话
	 * @param companyAddress
	 *            公司地址
	 * @param country
	 *            国家
	 * @param remark
	 *            备注
	 * @param companyName
	 *            公司名字
	 * @param companyLevel
	 *            公司等级
	 * @param legal
	 *            法人
	 * @param contactName
	 *            联系人姓名
	 * @param bsIntroduction
	 *            业务介绍
	 * @param changedCompanyLevel
	 *            修改后的企业等级
	 * @param startTime
	 *            生效开始时间
	 * @param lastLogin
	 *            最后登录时间
	 * @param businessLicence
	 *            营业执照
	 * @param taxRegistration
	 *            税务登记证
	 * @param legalIdentity
	 *            法人代表的身份证
	 * @param contract
	 *            合同
	 * @param activated
	 *            是否有效
	 * @param openid
	 *            微信登录的唯一标识
	 * @return
	 */
	@RequestMapping("/user/modifyUser")
	public Object modifyUser(HttpServletRequest request, String userId, String userName, String chinaName,
			String englishName, String nickName, MultipartFile icon, String birthday, String gender, String otherMobile,
			String companyAddress, String country, String remark, String companyName, String companyLevel, String legal,
			String contactName, String bsIntroduction, String changedCompanyLevel, String startTime, String lastLogin,
			MultipartFile businessLicence, MultipartFile taxRegistration, MultipartFile legalIdentity,
			MultipartFile contract, String activated, String openid) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		User user = new User();
		changedCompanyLevel = "2";
		startTime = "2017-11-11";
		// 校验参数
		if (StringUtils.isEmpty(userId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("用户Id不能为空");
			return resultInfo;
		}
		user.setUserId(Integer.parseInt(userId));
		if (!StringUtils.isEmpty(changedCompanyLevel)) {
			user.setChangedCompanyLevel(Integer.parseInt(changedCompanyLevel));
		}
		if (!StringUtils.isEmpty(startTime)) {
			try {
				user.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
			} catch (ParseException e) {
				resultInfo.setMessage("设置等级修改生效时间异常");
				resultInfo.setCode("-1");
			}
		}
		if (!StringUtils.isEmpty(userName)) {
			user.setUserName(userName);
		}
		if (!StringUtils.isEmpty(chinaName)) {
			user.setChinaName(chinaName);
		}
		if (!StringUtils.isEmpty(englishName)) {
			user.setEnglishName(englishName);
		}
		if (!StringUtils.isEmpty(nickName)) {
			user.setNickName(nickName);
		}
		if (!StringUtils.isEmpty(birthday)) {
			try {
				user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			} catch (ParseException e) {
				resultInfo.setMessage("设置生日异常");
				resultInfo.setCode("-1");
			}
		}
		if (!StringUtils.isEmpty(gender)) {
			user.setGender(Integer.parseInt(gender));
		}
		if (!StringUtils.isEmpty(otherMobile)) {
			user.setOtherMobile(otherMobile);
		}
		if (!StringUtils.isEmpty(companyAddress)) {
			user.setCompanyAddress(companyAddress);
		}
		if (!StringUtils.isEmpty(country)) {
			user.setCountry(country);
		}
		if (!StringUtils.isEmpty(remark)) {
			user.setRemark(remark);
		}
		if (!StringUtils.isEmpty(companyName)) {
			user.setCompanyName(companyName);
		}
		if (!StringUtils.isEmpty(legal)) {
			user.setLegal(legal);
		}
		if (!StringUtils.isEmpty(contactName)) {
			user.setContactName(contactName);
		}
		if (!StringUtils.isEmpty(bsIntroduction)) {
			user.setBsIntroduction(bsIntroduction);
		}

		if (!StringUtils.isEmpty(lastLogin)) {
			try {
				user.setLastLogin(new SimpleDateFormat("yyyy-MM-dd").parse(lastLogin));
			} catch (ParseException e) {
				resultInfo.setMessage("设置最后登录日期异常");
				resultInfo.setCode("-1");
			}
		}
		if (!StringUtils.isEmpty(activated)) {
			user.setActivated(Integer.parseInt(activated));
		}
		if (!StringUtils.isEmpty(openid)) {
			user.setOpenid(openid);
		}
		// 上传头像
		if (icon != null) {
			String imageName = System.currentTimeMillis() + icon.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("icon", imageName, icon);
				user.setIcon(imageUrl);
			} catch (Exception e) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("上传头像失败");
				return resultInfo;
			}
		}
		// 上传营业执照
		if (businessLicence != null) {
			String imageName = System.currentTimeMillis() + businessLicence.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("icon", imageName, businessLicence);
				user.setBusinessLicence(imageUrl);
			} catch (Exception e) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("上传营业执照失败");
				return resultInfo;
			}
		}
		// 上传税务登记证
		if (taxRegistration != null) {
			String imageName = System.currentTimeMillis() + taxRegistration.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("icon", imageName, taxRegistration);
				user.setTaxRegistration(imageUrl);
			} catch (Exception e) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("上传税务登记证失败");
				return resultInfo;
			}
		}
		// 上传法人代表的身份证
		if (legalIdentity != null) {
			String imageName = System.currentTimeMillis() + legalIdentity.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("icon", imageName, legalIdentity);
				user.setLegalIdentity(imageUrl);
			} catch (Exception e) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("上传法人代表的身份证失败");
				return resultInfo;
			}
		}
		// 上传合同
		if (contract != null) {
			String imageName = System.currentTimeMillis() + contract.getOriginalFilename();
			String imageUrl = null;
			try {
				imageUrl = UploadFileUtils.uploadFile("icon", imageName, contract);
				user.setContract(imageUrl);
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setCode("-1");
				resultInfo.setMessage("上传合同失败");
				return resultInfo;
			}
		}
		// 封装数据
		// 调用service层的方法
		try {
			Object object = userService.modifyUser(user);
			return object;
		} catch (Exception e) {
			System.out.println(e);
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改异常");
			return resultInfo;
		}
	}

}
