package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yilan.elantrip.domain.Insurance;
import com.yilan.elantrip.domain.InsuranceImage;
import com.yilan.elantrip.service.InsuranceService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

/**
 * 保险的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class InsuranceController {

	@Resource
	private InsuranceService insuranceService; // 注入

	/**
	 * 获取保险详情
	 * 
	 * @param insuranceId
	 *            保险Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/insurance/getInsurance")
	public Object getInsurance(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String insuranceId = request.getParameter("insuranceId");

		// 校验参数
		if (StringUtils.isEmpty(insuranceId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("保险Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = insuranceService.getInsuranceById(Integer.parseInt(insuranceId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加保险
	 * 
	 * @param num
	 *            保险编号
	 * @param title
	 *            主题
	 * @param classify
	 *            分类
	 * @param type
	 *            种类
	 * @param detail
	 *            详情
	 * @param price
	 *            价格
	 * @param activated
	 *            是否生效
	 * @param insuranceImage
	 *            图片
	 * @param request
	 * @return
	 */
	@RequestMapping("/insurance/addInsurance")
	public Object addInsurance(String num, String title, String classify, String type, String detail, String price,
			String activated, @RequestParam(value = "appImage", required = false) MultipartFile[] insuranceImage) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 校验参数
		if (StringUtils.isEmpty(num)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险编号不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(title)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险主题不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(classify)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险分类不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(type)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险种类不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(detail)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险详情不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(price)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险价格不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(activated)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("保险状态不能为空");
			return resultInfo;
		}
		if (insuranceImage != null && insuranceImage.length > 5) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("最多只能上传5张图片");
			return resultInfo;
		}
		// 封装数据
		Insurance insurance = new Insurance();
		List<InsuranceImage> insuranceImages = new ArrayList<InsuranceImage>();
		insurance.setNum(num);
		insurance.setTitle(title);
		insurance.setClassify(Integer.parseInt(classify));
		insurance.setType(Integer.parseInt(type));
		insurance.setDetail(detail);
		insurance.setPrice(Double.valueOf(price));
		insurance.setActivated(Integer.parseInt(activated));
		insurance.setCreatedDate(new Date());
		// 处理图片

		if (insuranceImage != null) {
			for (MultipartFile imageFile : insuranceImage) {
				String insuranceImageFileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("insurance", insuranceImageFileName, imageFile);
					InsuranceImage image = new InsuranceImage();
					image.setImageUrl(imageUrl);
					image.setCreatedDate(new Date());
					insuranceImages.add(image);
				} catch (Exception e) {
					e.printStackTrace();
					resultInfo.setCode("-1");
					resultInfo.setMessage("上传保险图片失败");
					return resultInfo;
				}
			}

		}else {
			InsuranceImage image = new InsuranceImage();
		    image.setImageUrl("null");
		    insuranceImages.add(image);
		}

		// 调用service层的方法

		try {
			Object object = insuranceService.addInsurance(insurance, insuranceImages);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加公告异常");
			return resultInfo;
		}
	}

	/**
	 * 获取保险列表
	 * 
	 * @param num
	 *            保险编号
	 * @param title
	 *            主题
	 * @param classify
	 *            分类
	 * @param type
	 *            种类
	 * @param detail
	 *            详情
	 * @param price
	 *            价格
	 * @param activated
	 *            是否生效
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insurance/getInsuranceList")
	public @ResponseBody Object getInsuranceList(String num, String title, String classiyf, String type,
			String activated, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (!StringUtils.isEmpty(num)) {
			params.put("num", num);
		}
		if (!StringUtils.isEmpty(title)) {
			params.put("title", title);
		}
		if (!StringUtils.isEmpty(classiyf)) {
			params.put("classiyf", classiyf);
		}
		if (!StringUtils.isEmpty(type)) {
			params.put("type", type);
		}
		if (!StringUtils.isEmpty(activated)) {
			params.put("activated", activated);
		}
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
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) insuranceService.countTotal(pageTool);
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

		List<Insurance> insuranceList = null;
		try {
			insuranceList = insuranceService.getInsuranceList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (insuranceList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Insurance>());
			return reInfo;
		}
		reInfo.setCode("0");
		reInfo.setResult(insuranceList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 修改保险
	 * 
	 * @param insuranceId
	 *            保险ID
	 * @param num
	 *            保险编号
	 * @param title
	 *            主题
	 * @param classify
	 *            分类
	 * @param type
	 *            种类
	 * @param detail
	 *            详情
	 * @param price
	 *            价格
	 * @param activated
	 *            是否生效
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insurance/modifyInsurance")
	public @ResponseBody Object modifyInsurance(String insuranceId, String num, String title, String classify,
			String type, String detail, String price, String activated, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		Insurance insurance = new Insurance();
		if (insuranceId == null || insuranceId.equals("")) {
			reInfo.setMessage("ID不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		insurance.setInsuranceId(Integer.parseInt(insuranceId));
		if (!StringUtils.isEmpty(num)) {
			insurance.setNum(num);
		}
		if (!StringUtils.isEmpty(title)) {
			insurance.setTitle(title);
		}
		if (!StringUtils.isEmpty(classify)) {
			insurance.setClassify(Integer.parseInt(classify));
		}
		if (!StringUtils.isEmpty(type)) {
			insurance.setType(Integer.parseInt(type));
		}
		if (!StringUtils.isEmpty(detail)) {
			insurance.setNum(detail);
		}
		if (!StringUtils.isEmpty(price)) {
			insurance.setPrice(Double.valueOf(price));
		}
		if (!StringUtils.isEmpty(activated)) {
			insurance.setActivated(Integer.parseInt(activated));
		}
		insurance.setUpdatedDate(new Date());
		int count = 0;
		try {
			count = (int) insuranceService.modifyInsurance(insurance);
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
		reInfo.setResult(count);
		return reInfo;
	}

}
