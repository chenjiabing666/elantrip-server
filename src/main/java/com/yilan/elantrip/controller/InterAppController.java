package com.yilan.elantrip.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.yilan.elantrip.domain.InterApp;
import com.yilan.elantrip.domain.News;
import com.yilan.elantrip.service.InterAppService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
//import com.yilan.elantrip.util.UploadFileUtils;

@RestController
public class InterAppController {
	@Resource
	InterAppService interAppService;
	
    /**
     * 合同申请填写
     * @param agreement
     * @param coAccount
     * @param coLevel
     * @param coName
     * @param addr
     * @param remark
     * @param legalPerson
     * @param appPerson
     * @param email
     * @param createDate
     * @return
     */
	@RequestMapping("/InterApp/addInterApp")
	Object getInterApp(
			@RequestParam 
			(value = "AgreementUrl", required = false) MultipartFile agreement,
			String coAccount,
			String coLevel,
			String coName,
			String addr,
			String remark,
			String legalPerson,
			String appPerson,
			String email,
			String createDate
			 ){
         ResultInfo reInfo = new ResultInfo();
         
         if (StringUtils.isEmpty(coAccount)) {
        	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
        	 reInfo.setMessage("企业账号不能为空");
 			return reInfo;
 		}
         if (StringUtils.isEmpty(coLevel)) {
        	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
        	 reInfo.setMessage("请选择企业等级");
 			return reInfo;
 		}        
         if (StringUtils.isEmpty(addr)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请填写地址");
			return reInfo;
		}         
          
         if (StringUtils.isEmpty(remark)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请填写备注");
			return reInfo;
		}
         if (StringUtils.isEmpty(legalPerson)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请填写法人");
			return reInfo;
		}
         if (StringUtils.isEmpty(appPerson)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请填写联系人");
			return reInfo;
		}
         if (StringUtils.isEmpty(email)) {
       	 reInfo.setCode("-1");    //设置状态码，成功为0，失败为-1
       	 reInfo.setMessage("请填写邮箱");
			return reInfo;
		}
         
		InterApp interApp = new InterApp();

//		String agreementFiles = System.currentTimeMillis() + agreement.getOriginalFilename();
//		String fileUrl = null;
//		try {
//			fileUrl = UploadFileUtils.uploadFile("fileUrl", agreementFiles, agreement);
//		} catch (Exception e) {
//			e.printStackTrace();
//			reInfo.setCode("-1");
//			reInfo.setMessage("上传文件失败，请重新上传");
//			return reInfo;
//		}

         //数据封装
         interApp.setCompanyName(coName);
         interApp.setApplicationName(appPerson);
         interApp.setAddr(addr);
         interApp.seteMail(email);
         interApp.setCompanyId(coAccount);
         interApp.setLevelId(Integer.valueOf(coLevel));
         interApp.setLegalPerson(legalPerson);
         interApp.setRemark(remark);
//	     interApp.setAgreement(fileUrl);
	     interApp.setCreatedDate(new Date());
	     
	     try {
	    	 //调用Service
			Object object = interAppService.addInterApp(interApp);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("添加异常");
			return reInfo;		
			}
         
	}
	
	/**
	 * 获取企业用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/InterApp/getInterList")
	public @ResponseBody Object getInterList(HttpServletRequest request,HttpServletResponse response){
		ResultInfo reInfo=new ResultInfo();  //封装结果集 	
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String companyName = request.getParameter("companyName");
		String eMail = request.getParameter("eMail");
		String applicationName = request.getParameter("applicationName");
		String phone = request.getParameter("phone");
		String levelId = request.getParameter("levelId");
		String activated = request.getParameter("activated");
		String createdDate = request.getParameter("createdDate");
		String remark = request.getParameter("remark");
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

        if (applicationName != null && !applicationName.equals("")) {
			params.put("companyName", applicationName);
		}
        if (eMail != null && !eMail.equals("")) {
			params.put("eMail", eMail);
		}
        if (companyName != null && !companyName.equals("")) {
			params.put("companyName", companyName);
		}
        if (phone != null && !phone.equals("")) {
			params.put("phone", phone);
		}
        if (levelId != null && !levelId.equals("")) {
			params.put("levelId", levelId);
		}
        if (activated != null && !activated.equals("")) {
			params.put("activated", activated);
		}
        if (createdDate != null && !createdDate.equals("")) {
			params.put("createdDate", createdDate);
		}
        if (remark != null && !remark.equals("")) {
			params.put("remark", remark);
		}
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) interAppService.countTotal(pageTool);
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

		List<InterApp> interList= null;
		try {
			interList = interAppService.getInterList(pageTool);
			
		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (interList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<News>());
			return reInfo;
		}
		reInfo.setResult(interList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
	
}
