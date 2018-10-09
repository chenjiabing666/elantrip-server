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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yilan.elantrip.domain.ProdAcc;
import com.yilan.elantrip.domain.ProdBright;
import com.yilan.elantrip.domain.ProdCar;
import com.yilan.elantrip.domain.ProdDescImage;
import com.yilan.elantrip.domain.ProdIncOther;
import com.yilan.elantrip.domain.ProdIntro;
import com.yilan.elantrip.domain.ProdIntroImage;
import com.yilan.elantrip.domain.ProdLanguage;
import com.yilan.elantrip.domain.ProdOther;
import com.yilan.elantrip.domain.ProdRestaurant;
import com.yilan.elantrip.domain.ProdStock;
import com.yilan.elantrip.domain.ProdTicket;
import com.yilan.elantrip.domain.ProdTraffic;

import com.yilan.elantrip.domain.Prod;
import com.yilan.elantrip.domain.ProdAttentionDanger;
import com.yilan.elantrip.domain.ProdAttentionOther;
import com.yilan.elantrip.domain.ProdAttentionRelay;
import com.yilan.elantrip.domain.ProdAttentionRemind;
import com.yilan.elantrip.domain.ProdAttentionShop;
import com.yilan.elantrip.domain.ProdAttentionTime;
import com.yilan.elantrip.domain.ProdPinfo;
import com.yilan.elantrip.domain.ProdPrimaryImage;
import com.yilan.elantrip.domain.ProdUseage;
import com.yilan.elantrip.domain.ProdVideo;
import com.yilan.elantrip.service.ProductService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
import com.yilan.elantrip.util.UploadFileUtils;

/**
 * 产品的Controller
 * 
 * @author Administrator
 *
 */
@RestController // 直接使用@RestController，不用@ResponseBody
public class ProductController {

	@Resource
	private ProductService productService; // 注入

	/**
	 * 获取产品详情
	 * 
	 * @param productId
	 *            产品Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/getProduct")
	public Object getProduct(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo();
		String productId = request.getParameter("productId");

		// 校验参数
		if (StringUtils.isEmpty(productId)) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("产品Id不能为空");
			return resultInfo;
		}

		// 调用service层的方法
		try {
			Object object = productService.getProductById(Integer.parseInt(productId));
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取异常");
			return resultInfo;
		}
	}

	/**
	 * 添加产品基本信息
	 * 
	 * @param prodCode
	 *            产品编号
	 * @param title
	 *            标题
	 * @param fromLocation
	 *            出发地
	 * @param toLocation
	 *            目的地
	 * @param typeId
	 *            分类id
	 * @param areaType
	 *            旅游区域
	 * @param days
	 *            行程天数
	 * @param productMainImage
	 *            主图
	 * @param productMinorImages
	 *            缩略图
	 * @param productVideo
	 *            视频
	 * @param insurance
	 *            保险
	 * @param region
	 *            所属地区
	 * @param slice
	 *            所属片区
	 * @param admin
	 *            创建人
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/addProductBasicInfo")
	public Object addProductBasicInfo(String prodCode, String title, String fromLocation, String toLocation,
			String typeId, String areaType, String days,
			@RequestParam(value = "productMainImage", required = false) MultipartFile productMainImage, String admin,
			@RequestParam(value = "productMinorImage", required = false) MultipartFile[] productMinorImages,
			@RequestParam(value = "productVideo", required = false) MultipartFile productVideo, String insurance,
			String region, String slice) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// prodCode = "12345";
		// title = "旅游";
		// fromLocation = "成都";
		// toLocation = "北京";
		// typeId = "1";
		// areaType = "1";
		// days = "1";
		// admin = "管理员";
		// insurance = "1,2";
		// region = "非洲";
		// slice = "南非";
		// 校验参数
		if (StringUtils.isEmpty(prodCode)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("产品编号不能为空");
			return resultInfo;
		}

		if (StringUtils.isEmpty(title)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("产品标题不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(fromLocation)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("出发地不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(toLocation)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("目的地不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(typeId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("产品种类不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(areaType)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("旅游区域不能为空");
			return resultInfo;
		}
		if (productMinorImages == null) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("缩略图不能为空");
			return resultInfo;
		}
		if (productMainImage == null) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("主图不能为空");
			return resultInfo;
		}
		if (productVideo == null) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("视频不能为空");
			return resultInfo;
		}
		if (productMinorImages != null && productMinorImages.length > 8) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("最多只能上传8张缩略图图片");
			return resultInfo;
		}
		// 封装
		Prod product = new Prod();
		product.setProdCode(Integer.parseInt(prodCode));
		product.setProdTitle(title);
		product.setTypeId(Integer.parseInt(typeId));
		product.setAreaType(Integer.parseInt(areaType));
		product.setFromLocation(fromLocation);
		product.setToLocation(toLocation);
		product.setInsurance(insurance);
		product.setCreatedDate(new Date());
		if (!StringUtils.isEmpty(days)) {
			product.setDays(Integer.parseInt(days));
		}
		if (!StringUtils.isEmpty(region)) {
			product.setRegion(region);
		}
		if (!StringUtils.isEmpty(slice)) {
			product.setSlice(slice);
		}
		product.setAdminName(admin);
		ProdPrimaryImage image = new ProdPrimaryImage();
		ProdVideo pproductVideo = new ProdVideo();
		StringBuffer thumbnailUrl = new StringBuffer();
		// 处理图片及视频
		// 处理缩略图
		if (productMinorImages != null) {
			for (MultipartFile imageFile : productMinorImages) {
				String productMinorImageFileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String imageUrl = null;
				try {
					imageUrl = UploadFileUtils.uploadFile("productMinorImage", productMinorImageFileName, imageFile);
					thumbnailUrl.append(imageUrl + "@");
				} catch (Exception e) {
					e.printStackTrace();
					resultInfo.setCode("-1");
					resultInfo.setMessage("上传缩略图失败");
					return resultInfo;
				}
			}
			image.setThumbnailUrl(thumbnailUrl.toString());
		}
		// 处理主图
		String imageFileName = System.currentTimeMillis() + productMainImage.getOriginalFilename();
		String imageUrl = null;
		try {
			imageUrl = UploadFileUtils.uploadFile("productMainImage", imageFileName, productMainImage);
			image.setImageUrl(imageUrl);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("上传主图失败");
			return resultInfo;
		}
		String videoFileName = System.currentTimeMillis() + productVideo.getOriginalFilename();
		String videoUrl = null;
		try {
			videoUrl = UploadFileUtils.uploadFile("productVideo", videoFileName, productVideo);
			pproductVideo.setVideoUrl(videoUrl);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("上传视频失败");
			return resultInfo;
		}
		// image.setImageUrl("0000");
		// image.setThumbnailUrl("1111");
		// pproductVideo.setVideoUrl("00000");
		// 处理视频
		// 调用service层的方法
		try {
			Object object = productService.addProductBasic(product, image, pproductVideo);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加产品异常");
			return resultInfo;
		}
	}

	/**
	 * 添加产品行程介绍
	 * 
	 * @param productId
	 *            产品Id
	 * @param request
	 * @return
	 */

	@RequestMapping("/product/addProdIntro")
	public @ResponseBody Object addProdIntro(
			@RequestParam(value = "introImage", required = false) MultipartFile[] introImage,
			HttpServletRequest request) {

		ResultInfo reInfo = new ResultInfo(); // 封装结果集

		ProdIntro prodIntro = new ProdIntro();
		ProdIntroImage prodIntroImage = new ProdIntroImage();
		List<ProdIntro> prodIntros = new ArrayList<>();
		List<ProdIntroImage> introImages = new ArrayList<ProdIntroImage>();
        
		String prodId = request.getParameter("prodId");//产品ID
		String tripDetails = request.getParameter("tripDetails");// 行程细节
		String tripSum = request.getParameter("tripSum");// 行程概述
		String days = request.getParameter("days");// 新增天数
		String views = request.getParameter("views");//景点
		String breakfast = request.getParameter("breakfast");
		String lunch = request.getParameter("lunch");
		String dinner = request.getParameter("dinner");
		String traffic = request.getParameter("traffic");
		String trafficOther = request.getParameter("trafficeOther");
		String hotel = request.getParameter("hotel");


		if (tripDetails == null || tripDetails.equals("")) {
			reInfo.setMessage("产品细节不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (tripSum == null || tripSum.equals("")) {
			reInfo.setMessage("产品概述不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (days != null) {
			prodIntro.setDays(Integer.valueOf(days));
		}

		if (introImage != null && introImage.length > 3) {
			reInfo.setCode("-1");
			reInfo.setMessage("最多只能上传3张图片");
			return reInfo;
		}
		
		
		// 处理图片
		if (introImage != null) {
			for (MultipartFile imageFile : introImage) {
				String introImageFile = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String introImageUrl = null;
				try {
					introImageUrl = UploadFileUtils.uploadFile("news", introImageFile, imageFile);
                    prodIntroImage.setImageUrl(introImageUrl);
                    prodIntroImage.setProdId(Integer.valueOf(prodId));
                    prodIntroImage.setCreatedDate(new Date());
        			prodIntroImage.setDays(Integer.valueOf(days));
					introImages.add(prodIntroImage);
				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setCode("-1");
					reInfo.setMessage("上传新闻图片失败");
					return reInfo;
				}
			}
		} else {
			prodIntroImage.setImageUrl("null");
			introImages.add(prodIntroImage);
		}

		// 其他数据封装
		prodIntro.setProdId(Integer.valueOf(prodId));
		prodIntro.setTripDetails(tripDetails);
		prodIntro.setTripSum(tripSum);
		prodIntro.setBreakfast(breakfast);
		prodIntro.setDinner(dinner);
		prodIntro.setHotels(hotel);
		prodIntro.setTrafficOther(trafficOther);
		prodIntro.setTrafficType(Integer.valueOf(traffic));
		prodIntro.setViews(views);
		prodIntro.setLunch(lunch);
		prodIntros.add(prodIntro);
		

		try {
			Object object = productService.addProdIntro(introImages, prodIntros);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setCode("-1");
			reInfo.setMessage("添加产品信息异常");
			return reInfo;
		}

	}

	/**
	 * 添加产品购买须知下的使用方法
	 * 
	 * @param prodId
	 *            产品Id
	 * @param tripConfirm
	 *            行程确认单
	 * @param appointment
	 *            预约
	 * @param confirmMessage
	 *            出行信息确认
	 * @param holiday
	 *            节假日顺延
	 * @param tripDay
	 *            出行当日请凭
	 * @param otherRemark
	 *            其他
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/addProdUseage")
	public Object addProdUseage(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String prodId = request.getParameter("prodId");
		String tripConfirm = request.getParameter("tripConfirm");
		String appointment = request.getParameter("appointment");
		String confirmMessage = request.getParameter("confirmMessage");
		String holiday = request.getParameter("holiday");
		String tripDay = request.getParameter("tripDay");
		String otherRemark = request.getParameter("otherRemark");
		// 校验参数
		if (StringUtils.isEmpty(prodId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("产品id不能为空");
			return resultInfo;
		}
		if (StringUtils.isEmpty(tripDay)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("出行当日请凭不能为空");
			return resultInfo;
		}
		// 封装数据
		ProdUseage prodUseage = new ProdUseage();
		prodUseage.setCreatedDate(new Date());
		prodUseage.setProdId(Integer.parseInt(prodId));
		prodUseage.setTripDay(tripDay);
		if (!StringUtils.isEmpty(otherRemark)) {
			prodUseage.setOtherRemark(otherRemark);
		}

		if (!StringUtils.isEmpty(tripConfirm)) {
			prodUseage.setTripConfirm(tripConfirm);
		}
		if (StringUtils.isEmpty(appointment)) {
			prodUseage.setAppointment(appointment);

		}
		if (StringUtils.isEmpty(confirmMessage)) {
			prodUseage.setConfirmMessage(confirmMessage);
		}
		if (StringUtils.isEmpty(holiday)) {
			prodUseage.setHoliday(holiday);
		}
		// 调用service层的方法
		Object object = null;
		try {
			object = productService.addProdUseAge(prodUseage);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加异常");
			return resultInfo;
		}
	}

	/**
	 * 添加产品购买须知下的注意事项
	 * 
	 * @param relayLlist
	 *            接送/集合
	 * @param shopList
	 *            购物
	 * @param remindList
	 *            温馨提示
	 * @param dangerList
	 *            安全警告
	 * @param otherList
	 *            其他
	 * @param timeList
	 *            时令公告
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/addProdAttention")
	public Object addProdAttention(HttpServletRequest request) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 获取参数
		String relay = request.getParameter("relayList");
		String shop = request.getParameter("shopList");
		String remind = request.getParameter("remindList");
		String danger = request.getParameter("dangerList");
		String other = request.getParameter("otherList");
		String time = request.getParameter("timeList");
		// 封装数据
		List<ProdAttentionRelay> prodAttentionRelays = new ArrayList<>();
		List<ProdAttentionRemind> prodAttentionReminds = new ArrayList<>();
		List<ProdAttentionDanger> prodAttentionDangers = new ArrayList<>();
		List<ProdAttentionOther> prodAttentionOthers = new ArrayList<>();
		List<ProdAttentionShop> prodAttentionShops = new ArrayList<>();
		List<ProdAttentionTime> prodAttentionTimes = new ArrayList<>();
		// 校验参数
		Gson gson = new Gson();// 创建一个Gson对象
		JsonParser parser = new JsonParser();
		if (!StringUtils.isEmpty(relay)) {
			JsonArray jarray = parser.parse(relay).getAsJsonArray();
			for (JsonElement jsonElement : jarray) {
				prodAttentionRelays.add(gson.fromJson(jsonElement, ProdAttentionRelay.class));
			}
		}
		if (!StringUtils.isEmpty(shop)) {
			JsonArray jarray1 = parser.parse(shop).getAsJsonArray();
			for (JsonElement jsonElement : jarray1) {
				prodAttentionReminds.add(gson.fromJson(jsonElement, ProdAttentionRemind.class));
			}
		}
		if (!StringUtils.isEmpty(remind)) {
			JsonArray jarray2 = parser.parse(remind).getAsJsonArray();
			for (JsonElement jsonElement : jarray2) {
				prodAttentionDangers.add(gson.fromJson(jsonElement, ProdAttentionDanger.class));
			}
		}
		if (!StringUtils.isEmpty(time)) {
			JsonArray jarray3 = parser.parse(time).getAsJsonArray();
			for (JsonElement jsonElement : jarray3) {
				prodAttentionOthers.add(gson.fromJson(jsonElement, ProdAttentionOther.class));
			}
		}
		if (!StringUtils.isEmpty(danger)) {
			JsonArray jarray4 = parser.parse(danger).getAsJsonArray();
			for (JsonElement jsonElement : jarray4) {
				prodAttentionShops.add(gson.fromJson(jsonElement, ProdAttentionShop.class));
			}
		}
		if (!StringUtils.isEmpty(other)) {
			JsonArray jarray5 = parser.parse(other).getAsJsonArray();
			for (JsonElement jsonElement : jarray5) {
				prodAttentionTimes.add(gson.fromJson(jsonElement, ProdAttentionTime.class));
			}
		}
		// 调用service层的方法
		Object object = null;
		try {
			object = productService.addProdAttention(prodAttentionRelays, prodAttentionShops, prodAttentionReminds,
					prodAttentionDangers, prodAttentionOthers, prodAttentionTimes);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加异常");
			return resultInfo;
		}
	}

	/**
	 * 添加/修改 退改规则
	 * 
	 * @param prodId
	 *            产品Id
	 * @param cancelType
	 *            取消类型
	 * @param supportPart
	 *            是否支持部分退
	 * @param modContactPerson
	 *            是否可以修改联系人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/addOrModifyRetreatingRule")
	public @ResponseBody Object addOrModifyRetreatingRule(String prodId, String cancelType, String supportPart,
			String modContactPerson, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		Prod product = new Prod();
		if (StringUtils.isEmpty(prodId)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(cancelType)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("取消类型不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(supportPart)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("是否支持部分退不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(modContactPerson)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("是否可以修改联系人不能为空");
			return reInfo;
		}
		product.setProdId(Integer.parseInt(prodId));
		product.setCancelType(Integer.parseInt(cancelType));
		product.setSupportPart(Integer.parseInt(supportPart));
		product.setModContactPerson(StringUtils.stringToInteger(modContactPerson));
		product.setUpdatedDate(new Date());
		int count = 0;
		try {
			count = (int) productService.modifyProduct(product);
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

	/**
	 * 添加产品人群信息
	 * 
	 * @param prodId
	 *            产品Id
	 * @param type
	 *            人群分类 1 成年人 2 儿童 3 婴儿 4 老人
	 * @param ageUp
	 *            年龄上限
	 * @param ageDown
	 *            年龄下限
	 * @param ageIsUp
	 *            是否包含上限 1 包含 0不包含
	 * @param ageIsDown
	 *            是否包含下限 1 包含 0 不包含
	 * @param heightDown
	 *            身高下限
	 * @param heightUp
	 *            身高上限
	 * @param hIsUp
	 *            身高是否包含上限 1 包含 0 不包含
	 * @param hIsDown
	 *            身高是否包含下限 1 包含 0 不包含
	 * @param remark
	 *            补充说明
	 * @param isJoin
	 *            是否可以参团 1 可以 0 不可以
	 * @param isAlone
	 *            是否可以单独预定 1 可以 0 不可以
	 * @param isFree
	 *            是否免费 1 是的 0 不是
	 * @param isInclTicket
	 *            是否包含门票 1 包含 0 不包含
	 * @param specialRemark
	 *            特殊说明的补充说明
	 * @param isInclCar
	 *            是否包含车位 1 包含 0 不包含现场支付导游 3 不包含按照成人预定
	 * @param carMoney
	 *            车位的钱 只有当不包含车位才能填值
	 * @param projectType
	 *            1 不含
	 * @param notProName
	 *            不包含的项目名称
	 * @param notJoinProName
	 *            不能参加的项目名称
	 * @param onlyProName
	 *            为了您的安全的项目名称
	 * @param onlyAge
	 *            仅限多少岁以上的儿童预定
	 * @param isAlready
	 *            是否选择了 本产品已为特惠。。。 1 已选 0 未选
	 * @param isDiscout
	 *            是否选择了门票优惠政策均按国。。。 1 已选 0 未选
	 * @param isReply
	 *            是否选中了最终是否享受优 1 0
	 * @param isHold
	 *            是否选中了持有...... 1 0
	 * @param certificateName
	 *            证件名称
	 * @param discount
	 *            优惠价格
	 * @param request
	 * @return
	 */

	@RequestMapping("/product/addProdPersonInfo")
	public Object addProdPersonInfo(HttpServletRequest request, String prodId, String type, String ageUp,
			String ageDown, String ageIsUp, String ageIsDown, String heightDown, String heightUp, String hIsUp,
			String hIsDown, String remark, String isJoin, String isAlone, String isFree, String isInclTicket,
			String specialRemark, String isInclCar, String carMoney, String projectType, String notProName,
			String notJoinProName, String onlyProName, String onlyAge, String isAlready, String isDiscout,
			String isReply, String isHold, String certificateName, String discount) {
		ResultInfo resultInfo = new ResultInfo(); // 封装结果集
		// 校验参数
		if (StringUtils.isEmpty(prodId)) {
			resultInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			resultInfo.setMessage("产品id不能为空");
			return resultInfo;
		}
		// 封装数据
		ProdPinfo prodPinfo = new ProdPinfo();
		ProdOther pOther = new ProdOther();
		prodPinfo.setProdId(Integer.parseInt(prodId));
		prodPinfo.setType(Integer.parseInt(type));
		prodPinfo.setAgeUp(Integer.parseInt(ageUp));
		prodPinfo.setAgeDown(Integer.parseInt(ageDown));
		prodPinfo.setAgeIsUp(Integer.parseInt(ageIsUp));
		prodPinfo.setAgeIsDown(Integer.parseInt(ageIsDown));
		prodPinfo.setHeightDown(Integer.parseInt(heightDown));
		prodPinfo.setHeightUp(Integer.parseInt(heightUp));
		prodPinfo.sethIsUp(Integer.parseInt(hIsUp));
		prodPinfo.sethIsDown(Integer.parseInt(hIsDown));
		prodPinfo.setRemark(remark);
		prodPinfo.setIsJoin(Integer.parseInt(isJoin));
		prodPinfo.setIsAlone(Integer.parseInt(isAlone));
		prodPinfo.setIsFree(Integer.parseInt(isFree));
		prodPinfo.setIsInclTicket(Integer.parseInt(isInclTicket));
		prodPinfo.setSpecialRemark(specialRemark);
		prodPinfo.setIsInclCar(Integer.parseInt(isInclCar));
		prodPinfo.setCarMoney(carMoney);
		prodPinfo.setProjectType(projectType);
		prodPinfo.setNotProName(notProName);
		prodPinfo.setNotJoinProName(notJoinProName);
		prodPinfo.setOnlyProName(onlyProName);
		prodPinfo.setOnlyAge(Integer.parseInt(onlyAge));
		pOther.setIsAlready(Integer.parseInt(isAlready));
		pOther.setIsDiscout(Integer.parseInt(isDiscout));
		pOther.setIsReply(Integer.parseInt(isReply));
		pOther.setIsHold(Integer.parseInt(isHold));
		pOther.setCertificateName(certificateName);
		pOther.setDiscount(Double.valueOf(discount));
		// 调用service层的方法
		Object object = null;
		try {
			object = productService.addProdPersonInfo(prodPinfo, pOther);
			return object;
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加异常");
			return resultInfo;
		}
	}

	/**
	 * 获取产品列表
	 * 
	 * @param prodCode
	 *            产品编号
	 * @param prodTitle
	 *            名称
	 * @param typeId
	 *            分类
	 * @param days
	 *            天数
	 * @param floorPrice
	 *            最低价格
	 * @param highestPrice
	 *            最高价格
	 * @param fromLocation
	 *            出发地
	 * @param areaType
	 *            旅游区域
	 * @param month
	 *            月份
	 * @param groundStatus
	 *            上/下架
	 * @param stockType
	 *            查询库存时的类型
	 * @param stock
	 *            库存数量
	 * @param infoCheck
	 *            信息审核（1/2:通过/不通过）3 未审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/getProductList")
	public @ResponseBody Object getProductList(String prodCode, String prodTitle, String typeId, String groundStatus,
			String stockType, String stock, String infoCheck, String days, String floorPrice, String highestPrice,
			String fromLocation, String areaType, String month, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		if (!StringUtils.isEmpty(days)) {
			params.put("days", days);
		}
		if (!StringUtils.isEmpty(floorPrice)) {
			params.put("floorPrice", floorPrice);
		}
		if (!StringUtils.isEmpty(highestPrice)) {
			params.put("highestPrice", highestPrice);
		}
		if (!StringUtils.isEmpty(areaType)) {
			params.put("areaType", areaType);
		}
		if (!StringUtils.isEmpty(fromLocation)) {
			params.put("fromLocation", fromLocation);
		}
		if (!StringUtils.isEmpty(month)) {
			params.put("month", month);
		}
		if (!StringUtils.isEmpty(prodCode)) {
			params.put("prodCode", prodCode);
		}
		if (!StringUtils.isEmpty(prodTitle)) {
			params.put("prodTitle", prodTitle);
		}
		if (!StringUtils.isEmpty(typeId)) {
			params.put("typeId", typeId);
		}
		if (!StringUtils.isEmpty(groundStatus)) {
			params.put("groundStatus", groundStatus);
		}
		if (!StringUtils.isEmpty(stockType)) {
			params.put("stockType", stockType);
		}
		if (!StringUtils.isEmpty(stock)) {
			params.put("stock", stock);
		}
		if (!StringUtils.isEmpty(infoCheck)) {
			params.put("infoCheck", infoCheck);
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
			totalCount = (int) productService.countTotal(pageTool);
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

		List<Prod> productList = null;
		try {
			productList = productService.getProductList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (productList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<Prod>());
			return reInfo;
		}
		reInfo.setResult(productList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}

	/**
	 * 修改产品
	 * 
	 * @param num
	 *            产品ID
	 * @param num
	 *            产品编号
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
	// @RequestMapping(value = "/product/modifyProduct")
	// public @ResponseBody Object modifyProduct(String productId, String num,
	// String title, String classify,
	// String type, String detail, String price, String activated,
	// HttpServletRequest request) {
	// ResultInfo reInfo = new ResultInfo(); // 封装结果集
	// Product product = new Product();
	// if (productId == null || productId.equals("")) {
	// reInfo.setMessage("ID不能为空！");
	// reInfo.setCode("-1");
	// return reInfo;
	// }
	// product.setProductId(Integer.parseInt(productId));
	// if (!StringUtils.isEmpty(num)) {
	// product.setNum(num);
	// }
	// if (!StringUtils.isEmpty(title)) {
	// product.setTitle(title);
	// }
	// if (!StringUtils.isEmpty(classify)) {
	// product.setClassify(Integer.parseInt(classify));
	// }
	// if (!StringUtils.isEmpty(type)) {
	// product.setType(Integer.parseInt(type));
	// }
	// if (!StringUtils.isEmpty(detail)) {
	// product.setNum(detail);
	// }
	// if (!StringUtils.isEmpty(price)) {
	// product.setPrice(Double.valueOf(price));
	// }
	// if (!StringUtils.isEmpty(activated)) {
	// product.setActivated(Integer.parseInt(activated));
	// }
	// product.setUpdatedDate(new Date());
	// int count = 0;
	// try {
	// count = (int) productService.modifyProduct(product);
	// } catch (Exception e) {
	// e.printStackTrace();
	// reInfo.setMessage("修改信息异常！");
	// reInfo.setCode("-1");
	// return reInfo;
	// }
	// if (count < 1) {
	// reInfo.setMessage("修改信息失败！");
	// reInfo.setCode("-1");
	// return reInfo;
	// }
	// reInfo.setMessage("修改信息成功！");
	// reInfo.setResult(count);
	// return reInfo;
	// }

	/**
	 * 添加产品描述和产品亮点
	 * 
	 * @param descImage
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/addProdBright")
	Object addProdBright(@RequestParam(value = "introImage", required = false) MultipartFile[] descImage,
			HttpServletRequest request) {

		ResultInfo reInfo = new ResultInfo();

		ProdBright prodBright = new ProdBright();

		String prodId = request.getParameter("prodId");
		String content = request.getParameter("content");

		if (content == null || content.equals("")) {
			reInfo.setMessage("产品概述不能为空！");
			reInfo.setCode("-1");
			return reInfo;
		} else {
			prodBright.setProdId(Integer.valueOf(prodId));
			prodBright.setContent(content);
		}

		// 处理图片
		List<ProdDescImage> descImages = new ArrayList<ProdDescImage>();
		ProdDescImage prodIntroImage = new ProdDescImage();
		if (descImage != null) {
			for (MultipartFile imageFile : descImage) {
				String descImageFile = System.currentTimeMillis() + imageFile.getOriginalFilename();
				String descImageUrl = null;

				try {
					descImageUrl = UploadFileUtils.uploadFile("news", descImageFile, imageFile);
					prodIntroImage.setImageUrl(descImageUrl);
					prodIntroImage.setCreatedDate(new Date());
					descImages.add(prodIntroImage);
				} catch (Exception e) {
					e.printStackTrace();
					reInfo.setCode("-1");
					reInfo.setMessage("上传新闻图片失败");
					return reInfo;
				}
			}

		} else {
			prodIntroImage.setImageUrl("null");
			descImages.add(prodIntroImage);
		}

		// 其他数据封装

		try {
			Object object = productService.addProdBright(descImages, prodBright);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			reInfo.setCode("-1");
			reInfo.setMessage("添加产品信息异常");
			return reInfo;
		}
	}

	/**
	 * 添加产品说明
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/addProdExplain")
	Object addProdExplain(HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo();

		ProdIncOther prodIncOther = new ProdIncOther();
		ProdRestaurant prodRestaurant = new ProdRestaurant();
		ProdTraffic prodTraffic = new ProdTraffic();
		ProdAcc prodAcc = new ProdAcc();
		ProdLanguage prodLanguage = new ProdLanguage();

		List<ProdTicket> prodTicketList = new ArrayList<>();
		List<ProdTraffic> prodTrafficList = new ArrayList<>();
		List<ProdAcc> prodAccList = new ArrayList<>();
		List<ProdRestaurant> prodRestaurantList = new ArrayList<>();
		List<ProdIncOther> prodOthersList = new ArrayList<>();

		String prodId = request.getParameter("prodId");
		String ticket = request.getParameter("ticket");
		String restaurant = request.getParameter("restaurant");
		String language = request.getParameter("language");
		String other = request.getParameter("other");
		String acc = request.getParameter("acc");
		String traffic = request.getParameter("traffic");

		if (ticket == null || ticket.equals("")) {
			reInfo.setMessage("请选择门票选项！");
			reInfo.setCode("-1");
		}
		if (restaurant == null || restaurant.equals("")) {
			reInfo.setMessage("请选择餐饮选项！");
			reInfo.setCode("-1");
		}

		if (language == null || language.equals("")) {
			reInfo.setMessage("请选择语言选项！");
			reInfo.setCode("-1");
		}
		if (other == null || other.equals("")) {
			reInfo.setMessage("请填写其他说明选项！");
			reInfo.setCode("-1");
		}
		if (acc == null || acc.equals("")) {
			reInfo.setMessage("请填写费用选项！");
			reInfo.setCode("-1");
		}
		if (traffic == null || traffic.equals("")) {
			reInfo.setMessage("请填写交通选项！");
			reInfo.setCode("-1");
		}

		prodIncOther.setProdId(Integer.valueOf(prodId));
		prodRestaurant.setProdId(Integer.valueOf(prodId));
		prodAcc.setProdId(Integer.valueOf(prodId));
		prodTraffic.setProdId(Integer.valueOf(prodId));
		prodLanguage.setProdId(Integer.valueOf(prodId));

		try {
			Object object = productService.addProdExplain(prodTicketList, prodTrafficList, prodAccList,
					prodRestaurantList, prodOthersList);
			return object;
		} catch (Exception e) {
			reInfo.setCode("-1");
			reInfo.setMessage("添加异常");
			return reInfo;
		}
	}

	/**
	 * 添加产品库存
	 * 
	 * @param prodId
	 * @param month
	 * @param year
	 * @param day
	 * @param total
	 * @param sold
	 * @param downPrice
	 * @param pType
	 * @param carType
	 * @param carLocation
	 * @param perDown
	 * @param perUp
	 * @param price
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/addProdStock")
	public @ResponseBody Object addProdStock(String prodId, String month, String year, String day, String total,
			String sold, String downPrice, String pType, String carType, String carLocation, String perDown,
			String perUp, String price, HttpServletRequest request) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		if (StringUtils.isEmpty(prodId)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(month)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(year)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(day)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(total)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(downPrice)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(pType)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("产品ID不能为空");
			return reInfo;
		}
		ProdCar prodCar = new ProdCar();
		prodCar.setProdId(Integer.parseInt(prodId));
		if (!StringUtils.isEmpty(carType)) {
			prodCar.setCarType(Integer.parseInt(carType));
		}
		if (!StringUtils.isEmpty(carLocation)) {
			prodCar.setCarLocation(Integer.parseInt(carLocation));
		}
		if (!StringUtils.isEmpty(perDown)) {
			prodCar.setPerDown(Integer.parseInt(perDown));
		}
		if (!StringUtils.isEmpty(perUp)) {
			prodCar.setPerUp(Integer.parseInt(perUp));
		}
		if (!StringUtils.isEmpty(price)) {
			prodCar.setPrice(Double.valueOf(price));
		}
		ProdStock prodStock = new ProdStock();
		prodStock.setProdId(Integer.parseInt(prodId));
		prodStock.setMonth(month);
		prodStock.setDay(day);
		prodStock.setYear(year);
		prodStock.setpType(Integer.parseInt(pType));
		prodStock.setTotal(Integer.parseInt(total));
		prodStock.setDownPrice(Double.valueOf(downPrice));
		List<ProdStock> prodStocks = new ArrayList<>();
		prodStocks.add(prodStock);
		int count = 0;
		try {
			count = (int) productService.addProdStock(prodCar, prodStocks);
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
		reInfo.setResult(count);
		return reInfo;
	}

	// @RequestMapping("/product/modifyStock")
	// Object modifyStock(
	// HttpServletRequest request) {
	//
	// String prodId = request.getParameter("prodId");
	// String day = request.getParameter("day");
	// String month = request.getParameter("month");
	// String year = request.getParameter("year");
	// String downPrice = request.getParameter("downPrice");
	// String total = request.getParameter("total");
	// String sale = request.getParameter("sale");
	// String stockDate = request.getParameter("stockDate");
	// String pType = request.getParameter("pType");
	//
	//
	// return request;
	//
	// }
}
