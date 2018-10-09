package com.yilan.elantrip.controller;

import java.text.ParseException;
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
import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.domain.CustomImages;
import com.yilan.elantrip.domain.CustomTrip;
import com.yilan.elantrip.domain.rs.RScustomeTrip;
import com.yilan.elantrip.domain.rs.Traveler;
import com.yilan.elantrip.service.CustomerAppService;
import com.yilan.elantrip.util.DateUtil;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

@RestController
public class CustomAppController {
    
	@Resource
	private CustomerAppService customerService;
	
	
	
	public CustomerAppService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerAppService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/CustomTrip/addTripOne")
	Object addTripOne(
			@RequestParam
			String tripType,
			String departure,
			String destination,
			String adult,
			String child,
			String startTime,
			String endTime
			) {
		CustomTrip customTrip = new CustomTrip();

		ResultInfo rsInfo = new ResultInfo();
		if (StringUtils.isEmpty(tripType)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("定制类型不能为空");
			return rsInfo;
		}
		if (StringUtils.isEmpty(departure)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("请输入出发地");
			return rsInfo;
		}
		if (StringUtils.isEmpty(destination)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("目的地不能为空");
			return rsInfo;
		}
		if (StringUtils.isEmpty(adult)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("成人人数");
			return rsInfo;
		}
		if (StringUtils.isEmpty(child)) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("儿童人数");
			return rsInfo;
		}
		if (startTime != null) {
			try {
				customTrip.setStartDate(DateUtil.getDateFromString(startTime));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}		
			}
		if (endTime != null) {
			try {
				customTrip.setEndDate(DateUtil.getDateFromString(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			}
	    
		//数据封装
		customTrip.setType(Integer.valueOf(tripType));
		customTrip.setFromLocation(departure);
		customTrip.setToLocation(destination);

		customTrip.setAdult(Integer.valueOf(adult));
		customTrip.setChild(Integer.valueOf(child));
	   
	    try{
	    	Object object = customerService.addtrip(customTrip);
	    	return object;
	    }catch(Exception e){
	    	rsInfo.setCode("-1");
			rsInfo.setMessage("添加定制错误");
			return rsInfo;
	    }
	}
	
	@RequestMapping("/CustomTrip/CusAddTripTwo")
    Object addTripTwo(
    		@RequestParam
    		String buget,
    		String hotelType,
    		String theme,
    		String roomNum,
    		String roomType,
    		String isSmooke,
    		String carType,
    		String carSize,
    		String isDis, //是否残疾
    		String foodSt, //用餐标准
    		String guideSt, //导游标准
    		String mustGo,
    		String otherReq
    		) {
		
		CustomTrip customTrip = new CustomTrip();
    	
    	ResultInfo reInfo = new ResultInfo(); 
    	if (buget == null || buget.equals("")) {
			reInfo.setMessage("请填写预算");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (hotelType == null || hotelType.equals("")) {
			reInfo.setMessage("选择酒店类型");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (theme == null || theme.equals("")) {
			reInfo.setMessage("旅游主题");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (roomType == null || roomType.equals("")) {
			reInfo.setMessage("请填写房间类型");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (isSmooke == null || isSmooke.equals("")) {
			reInfo.setMessage("房间是否需要吸烟");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (carSize == null || carSize.equals("")) {
			reInfo.setMessage("车辆大小");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (carType == null || carType.equals("")) {
			reInfo.setMessage("车辆类型");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (isDis == null || isDis.equals("")) {
			reInfo.setMessage("是否有残疾人");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (foodSt == null || foodSt.equals("")) {
			reInfo.setMessage("餐食标准");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if(guideSt == null || guideSt.equals("")) {
			reInfo.setMessage("导游标准-1");
			reInfo.setCode("-1");
			return reInfo;
    	}
    	if(roomNum == null || roomNum.equals("")) {
			reInfo.setMessage("房间数量-1");
			reInfo.setCode("-1");
			return reInfo;
    	}
    	if(mustGo != null&&!mustGo.equals("")) {
    		customTrip.setMustView(mustGo);
    	}
    	if(otherReq != null) {
    		customTrip.setOtherNeed(otherReq);
    	}
    	

    	
//    	数据封装
    	customTrip.setBuget(Double.valueOf(buget));
    	customTrip.setHotelNeed(Integer.valueOf(hotelType));
    	customTrip.setTripTheme(Integer.valueOf(theme));
    	customTrip.setRoomType(Integer.valueOf(roomType));
    	customTrip.setIsSmoke(Integer.valueOf(isSmooke));
    	customTrip.setCarType(Integer.valueOf(carType));
    	customTrip.setCarSize(Integer.valueOf(carSize));
    	customTrip.setIsNeedDis(Integer.valueOf(isDis));
    	customTrip.setFoodSta(Integer.valueOf(foodSt));
    	customTrip.setGuideSta(Integer.valueOf(guideSt));
    	customTrip.setRoomNum(Integer.valueOf(roomNum));

    	try {
			Object object = customerService.addTripTwo(customTrip);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("定制添加异常2");
			return reInfo;
		}	
    }
	
	@RequestMapping("/custom/addTripThree")
	Object addTripthree(@RequestParam String name, String phone, String email, String wechatNum) {
		
    CustomTrip customTrip = new CustomTrip();
    	
    	ResultInfo reInfo = new ResultInfo(); 
    	if (name == null || name.equals("")) {
			reInfo.setMessage("联系人姓名");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (phone == null || phone.equals("")) {
			reInfo.setMessage("电话为空");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (email == null || email.equals("")) {
			reInfo.setMessage("电子邮箱");
			reInfo.setCode("-1");
			return reInfo;
		}
    	if (wechatNum != null) {
    		customTrip.setContactWeixin(wechatNum);
		}
        
    	//数据封装
    	customTrip.setContactName(name);
    	customTrip.setContactMobile(phone);
    	customTrip.setContactEmail(email);
        
    	try {
			Object object = customerService.addTripThree(customTrip);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("定制添加异常3");
			return reInfo;
		}
		
	}
	
	@RequestMapping("/custom/placeOrder")
	Object placeOrder(
			@RequestParam(value = "tripDetail", required = false, defaultValue = "未上传") MultipartFile tripDetail,
			@RequestParam(value = "contract", required = false) MultipartFile contract,
			@RequestParam(value = "image", required = false) MultipartFile[] image,
			String userId, 
			String referee, 
			String productTitle, 
			String tripDesc,
			String tripCode,
			String price, 
			String payMethod, 
			String downPay, 
			String finalPay, 
			String dueTime,
		    String insuranceId,
			String totalPeople, 
			String invoiceId, 
			String bustripId,
			String remark,
			String cnName,
			String egName,
			String nation,
			String expireDate,
			String sex,
			String idInfo,
			String birthdate,
			String mobile
             ) {
		
        CustomTrip customTrip = new CustomTrip();
//        Traveler traveler = new Traveler();
        ResultInfo reInfo = new ResultInfo();
        
        List<CustomImages> imageList = new ArrayList<>();
        
		// 校验参数
        
        if (productTitle == null || productTitle.equals("")) {
			reInfo.setMessage("请填写产品标题");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (tripDesc == null || tripDesc.equals("")) {
			reInfo.setMessage("请填写行程描述");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (tripCode == null || tripCode.equals("")) {
			reInfo.setMessage("行程单号");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (price == null || price.equals("")) {
			reInfo.setMessage("定制行程价格");
			reInfo.setCode("-1");
			return reInfo;
		}

        if (payMethod == null || payMethod.equals("")) {
			reInfo.setMessage("选择支付方式");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (downPay == null || downPay.equals("")) {
			reInfo.setMessage("首付百分比");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (finalPay == null || finalPay.equals("")) {
			reInfo.setMessage("尾款百分比");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (dueTime == null || dueTime.equals("")) {
			reInfo.setMessage("最后付款时间");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (insuranceId == null || insuranceId.equals("")) {
			reInfo.setMessage("选择保险方案");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (totalPeople == null || totalPeople.equals("")) {
			reInfo.setMessage("总人数");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (invoiceId == null || invoiceId.equals("")) {
			reInfo.setMessage("发票信息");
			reInfo.setCode("-1");
			return reInfo;
		}
        if (remark == null || remark.equals("")) {
			reInfo.setMessage("备注");
			reInfo.setCode("-1");
			return reInfo;
		}
        
     // 行程单和合同上传
     		String tripDetails = System.currentTimeMillis()
     				+ tripDetail.getOriginalFilename(); // 安装包名称
            String contractFile = System.currentTimeMillis()
     				+ contract.getOriginalFilename();
     		String detailFileUrl = null;
     		String contractUrl = null;
     		try {
     			detailFileUrl = UploadFileUtils.uploadFile("tripDetails", tripDetails,
     					tripDetail);
     			try {
     				contractUrl = UploadFileUtils.uploadFile("contractFile", contractFile,
         					contract);
				} catch (Exception e) {
	     			e.printStackTrace();
	     			reInfo.setCode("-1");
	     			reInfo.setMessage("上传合同详情失败");
	     			return reInfo;
				}
     			customTrip.setFile(detailFileUrl);
     			customTrip.setContractUrl(contractUrl);
     			
     		} catch (Exception e) {
     			e.printStackTrace();
     			reInfo.setCode("-1");
     			reInfo.setMessage("上传行程详情失败");
     			return reInfo;
     		}

     		// 行程描述图片上传
     		for (MultipartFile imageFile : image) {
    			String detailImage = System.currentTimeMillis()
    					+ imageFile.getOriginalFilename();
    			String imageUrl = null;
    			try {
    				imageUrl = UploadFileUtils.uploadFile("app", detailImage,
    						imageFile);
    				CustomImages images = new CustomImages();
    				images.setImageUrl(imageUrl);
    				images.setCreatedDate(new Date());
    				imageList.add(images);
    			} catch (Exception e) {
    				e.printStackTrace();
    				reInfo.setCode("-1");
    				reInfo.setMessage("上传应用截图失败失败，请重新上传");
    				return reInfo;
    			}
    		}

        //其他数据封装
        customTrip.setProductTitle(productTitle);
        customTrip.setReferee(referee);
        customTrip.setTripDesc(tripDesc);
        customTrip.setTravelCode(Integer.valueOf(tripCode));
        customTrip.setPrice(Double.valueOf(price));
        customTrip.setPayMethod(Integer.valueOf(payMethod));
        customTrip.setDownPay(Double.valueOf(downPay));
        customTrip.setFinalPay(Double.valueOf(finalPay));
        customTrip.setFinalpayTime(dueTime);
        customTrip.setInvoiceId(Integer.valueOf(invoiceId));
        customTrip.setRemark(remark);
        customTrip.setInsuranceId(Integer.valueOf(insuranceId));
        customTrip.setTeenager(Integer.valueOf(totalPeople)); //总人数
        
//        List<Map<String, Object>> travelerList = new ArrayList<Map<String,Object>>();
//        Map<String,Object>travelerMap= new HashMap<String,Object>();
        List<Traveler> travelerList = new ArrayList<>();
        Traveler traveler = new Traveler();
        
        if (!StringUtils.isEmpty(egName)) {
        	traveler.setEgName(egName);
		}
		if (!StringUtils.isEmpty(cnName)) {
        	traveler.setEgName(cnName);
		}
		if (!StringUtils.isEmpty(nation)) {
        	traveler.setEgName(nation);
		}
		if (!StringUtils.isEmpty(expireDate)) {
			try {
				traveler.setExpireDate(DateUtil.getDateFromString(expireDate));
			} catch (ParseException e) {
				reInfo.setMessage("获取过期日错误");
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(sex)) {
        	traveler.setEgName(sex);
		}
		if (!StringUtils.isEmpty(idInfo)) {
        	traveler.setEgName(idInfo);
		}
		if (!StringUtils.isEmpty(birthdate)) {
			try {
				traveler.setBirthdate(DateUtil.getDateFromString(birthdate));
			} catch (ParseException e) {
				reInfo.setMessage("获取生日错误");
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(mobile)) {
        	traveler.setEgName(mobile);
		}
		
        travelerList.add(traveler);
    	//数据封装;
        
    	try {
			Object object = customerService.placeOrder(customTrip);

			Object object2 = customerService.placeOrderTrav(travelerList);
			
			Object object3 = customerService.addImages(imageList); 
			
			List<Object> listObject = new ArrayList<>();
			
			listObject.add(object);
			listObject.add(object2);
			listObject.add(object3);
			
			return listObject;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("定制添加异常3");
			return reInfo;
		}
		
	}
	
	
	@RequestMapping(value = "/customApp/getAppList")
	public @ResponseBody Object getAppList(HttpServletRequest request,
			HttpServletResponse response) {
		ResultInfo rsInfo = new ResultInfo();

		HashMap<String, Object> params = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String customId = request.getParameter("customId");

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

		if (customId != null && !customId.equals("")) {
			params.put("customId", customId);
		}

		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum),
				Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;
		try {
			totalCount = customerService.countTotal(pageTool);
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

		List<RScustomeTrip> cusTripList = null;
		try {
			cusTripList = customerService.getCusTripList(pageTool);
		} catch (Exception e) {
			rsInfo.setMessage("获取Bus环游列表异常！");
			rsInfo.setCode("-1");
			return rsInfo;
		}
		if (cusTripList.size() == 0) {
			rsInfo.setMessage("BUS环游列表为空！");
			rsInfo.setResult(new ArrayList<Banner>());
			return rsInfo;
		}
		rsInfo.setResult(cusTripList);
		rsInfo.setTotal(totalCount);
		rsInfo.setMessage("获取bus环游列表成功！");
		return rsInfo;
	}
	
}