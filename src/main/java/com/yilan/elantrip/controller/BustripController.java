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
import com.yilan.elantrip.domain.Bustrip;
import com.yilan.elantrip.domain.BustripNode;
import com.yilan.elantrip.domain.BustripStock;
import com.yilan.elantrip.domain.TripNode;
import com.yilan.elantrip.service.BustripService;
import com.yilan.elantrip.util.DateUtil;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@RestController
public class BustripController {
        
	@Resource
	private BustripService bustripService;

//	public BustripService getBustripService() {
//		return bustripService;
//	}
//	
//	
//	public void setBustripService(BustripService bustripService) {
//		this.bustripService = bustripService;
//	}
	
	
	/**
	 * 编辑巴士环线
	 * @param bustripImage
	 * @param request
	 * @return
	 */
	@RequestMapping("/busTrip/modifyBustrip")
	Object ModifyBustrip(
			@RequestParam(value = "bustripImage", required = false) MultipartFile[] bustripImage,String tripId,
			HttpServletRequest request) {
		
		//实例化对象
		ResultInfo resultInfo = new ResultInfo();
		Bustrip bustrip = new Bustrip();//实例化环线表
//		List<BustripImage> bustripImages = new ArrayList<BustripImage>();//实例化图片数组

	    //获取需要修改的参数
		String tripDesc = request.getParameter("tripDesc");//获取修改的环线描述

	    //参数校验
		if (tripId == null || tripId.equals("")) {
			resultInfo.setMessage("错误！未获取到环线ID！Controller");
			resultInfo.setCode("-1");
			return resultInfo;
		}

		
		if (tripDesc != null && !tripDesc.equals("")) {
//			bustrip.setTripId(Short.valueOf(bustripId));
			bustrip.setTripId(Short.valueOf(tripId));
			bustrip.setTripDesc(tripDesc);//传入修改的环线描述
			bustrip.setUpdatedDate(new Date());//传入修改日期
		}
		
		//添加到节点表
//		bustripNode.setBustripId(Integer.valueOf(tripId));
//		if (nodeCode != null && !nodeCode.equals("")) {
//			bustripNode.setNodeCode(nodeCode);
//			bustripNodes.add(bustripNode);
//			}
		

		
	
		
////		 //处理图片
//				if (bustripImage != null) {
//					for (MultipartFile BustripimageFile : bustripImage) {
//						String BustripImageFileName = System.currentTimeMillis() + BustripimageFile.getOriginalFilename();
//						String imageUrl = null;
//						try {
//							imageUrl = UploadFileUtils.uploadFile("bustripImage", BustripImageFileName, BustripimageFile);
//							BustripImage btImages = new BustripImage();
////							btImages.setImageUrl(imageUrl);
//							btImages.setImageUrl("111111111");
//							btImages.setBustripId(Integer.valueOf(tripId));
//							btImages.setCreatedDatetime(new Date());
//							bustripImages.add(btImages);
//						} catch (Exception e) {
//							e.printStackTrace();
//						    resultInfo.setCode("-1");
//						    resultInfo.setMessage("上传环线图片失败");
//							return resultInfo;
//						}
//					}
//				}else {
//					BustripImage btImages = new BustripImage();
//					btImages.setImageUrl("null");
//					bustripImages.add(btImages);
//					
//				}
				
				try {
					Object object = bustripService.modifyBustrip(bustrip);
					return object;
				}catch (Exception e) {
					resultInfo.setMessage("修改巴士环线异常--调用bustripService错误");
					return resultInfo;
				}				

	}
	/**
	 * 添加巴士环线节点
	 * @param nodeCode
	 * @param tripId
	 * @return
	 */
	@RequestMapping("/bustrip/addBustripNodes")
	Object addBustripNode(@RequestParam String nodeCode, String tripId) {
		ResultInfo resultInfo = new ResultInfo();
		BustripNode bustripNode = new BustripNode();
	    List<BustripNode> bustripNodes = new ArrayList<>();
		if(nodeCode!=null||!nodeCode.equals("")) {
			bustripNode.setNodeCode(nodeCode);
			bustripNode.setBustripId(Integer.valueOf(tripId));
		}
		
		bustripNodes.add(bustripNode);
		
		try {
			Object object = bustripService.addBustripNode(bustripNodes);
			return object;
		} catch (Exception e) {
			resultInfo.setMessage("添加节点失败-----Controller");
			return resultInfo;
		}
	}
	
	@RequestMapping("/bustrip/getBustripById")
	Object getBustripById(@RequestParam String tripId) {
		ResultInfo resultInfo = new ResultInfo();
        if(tripId==null||tripId.equals("")) {
        	resultInfo.setMessage("无法获取到巴士环游ID");
        	resultInfo.setCode("-1");
        }
        System.out.println(tripId);
        	try {
    			Object object = bustripService.getBustripById(Short.valueOf(tripId));
    			return object;
    		} catch (Exception e) {
    			resultInfo.setMessage("获取节点详情失败----Controller");
    			return resultInfo;
    		}
        
				
		
	}
	/**
	 * 编辑节点
	 * @param nodeDesc
	 * @param nodeId
	 * @return
	 */
	@RequestMapping("/busTrip/modifyNode")
	Object modifyNode(@RequestParam String nodeDesc, String nodeId) {
		
		ResultInfo resultInfo = new ResultInfo();
		TripNode tripNode = new TripNode();
		
		if (nodeId == null || nodeId.equals("")) {
			resultInfo.setMessage("节点Id不能为空！");
			resultInfo.setCode("-1");
			return resultInfo;
		}
				
		if (nodeDesc == null || nodeDesc.equals("")) {
			resultInfo.setMessage("节点描述不能为空！");
			resultInfo.setCode("-1");
			return resultInfo;
		}
		
		
		tripNode.setNodeDesc(nodeDesc);
	    tripNode.setNodeId(Integer.valueOf(nodeId));
	    
				try {
					Object object = bustripService.modifyNode(tripNode);
					return object;
				}catch (Exception e) {
					resultInfo.setMessage("修改node描述时，调用bustripService异常----Controller");
					return resultInfo;
				}				
	}
    /**
     * 编辑巴士环游库存
     * @param stockId
     * @param bustripId
     * @param month
     * @param year
     * @param day
     * @param total
     * @param sold
     * @param downPrice
     * @param stockDate
     * @return
     */
	@RequestMapping(value = "/busTrip/modifyBustripStock")
	public @ResponseBody Object addbustripStock( @RequestParam String stockId, String bustripId, String month, String year, String day, String total,
			String sold, String downPrice,String stockDate) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		BustripStock bustripStock = new BustripStock();
		if (StringUtils.isEmpty(stockId)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("未获取到库存ID，不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(bustripId)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("巴士环游ID不能为空");
			return reInfo;
		}
		if (StringUtils.isEmpty(stockDate)) {
			reInfo.setCode("-1"); // 设置状态码，成功为0，失败为-1
			reInfo.setMessage("库存日期不能为空");
			return reInfo;
		}
		
		if(total!=null && !total.equals("")) {
			bustripStock.setTotal(Integer.parseInt(total));

		}
		if(downPrice!=null && !downPrice.equals("")) {
			bustripStock.setDownPrice(Double.valueOf(downPrice));

		}
		if(sold!=null && !sold.equals("")) {
			bustripStock.setSold(Integer.valueOf(sold));

		}

		//获取修改的日期
		bustripStock.setBustripId(Integer.parseInt(bustripId));
		try {
			bustripStock.setStockDate(DateUtil.getDateFromString(stockDate));
		} catch (ParseException e1) {
			reInfo.setCode("-1");
			reInfo.setMessage("获取日期异常---Controller");
		}
	    List<BustripStock> bustripStocks = new ArrayList<>();
	    bustripStocks.add(bustripStock);

        try  {
        	Object object = bustripService.modifyBustripStock(bustripStocks);
            return object;
        
        } catch (Exception e) {
        	reInfo.setMessage("管理库存模块出错");
        	reInfo.setCode("-1");
        	return reInfo;
		}
    }
	/**
	 * 获取巴士环线列表
	 * @param pageNum
	 * @param pageSize
	 * @param tripName
	 * @param tripCode
	 * @return
	 */
	@RequestMapping(value = "/busTrip/getBusTripList")
	public @ResponseBody Object getBusTripList(@RequestParam 
			String pageNum,
			String pageSize,
			String tripName,
			String tripCode) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		Bustrip bustrip = new Bustrip();

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
		
		if(tripName!=null && !tripName.equals("")) {
			params.put("tripName", tripName);
		}
		
		if(tripCode!=null && !tripCode.equals("")) {
			params.put("tripCode", tripCode);
		}
		
		params.put("pageNum", pageNum);
		params.put("pageSize", pageSize);
		
		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		 System.out.println(params.get("pageNum"));
		 System.out.println(params.get("pageSize"));
		int totalCount = 0;
		try {
			totalCount = (int) bustripService.countTotal(pageTool);
			
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

		List<Bustrip> bustripList = null;
		try {
			bustripList = bustripService.getBusTripList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
//		if (bustripList.size() == 0) {
//			reInfo.setMessage("列表为空！");
//			return reInfo;
//		}
		reInfo.setResult(bustripList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
	
	/**
	 * 
	 * 获取节点列表
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/busTrip/getNodeList")
	public @ResponseBody Object getNodeList(HttpServletRequest request, HttpServletResponse response) {
		ResultInfo reInfo = new ResultInfo(); // 封装结果集
		HashMap<String, Object> params = new HashMap<String, Object>();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String nodeCode = request.getParameter("nodeCode");
		String nodeType = request.getParameter("nodeType");
		String cityName = request.getParameter("cityName");

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

		if (nodeCode != null && !nodeCode.equals("")) {
			params.put("nodeCode", nodeCode);
		}
		if (nodeType != null && !nodeType.equals("")) {
			params.put("nodeType", nodeType);
		}
		if (cityName != null && !cityName.equals("")) {
			params.put("nodeName", cityName);
		}

		PagingTool pageTool = new PagingTool(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		pageTool.setParams(params);
		int totalCount = 0;

		try {
			totalCount = (int) bustripService.countNodeTotal(pageTool);
		} catch (Exception e) {
			reInfo.setMessage("获取节点总数异常！");
			reInfo.setCode("-1");
			return reInfo;
		}

		if (totalCount == 0) {
			reInfo.setMessage("节点总数量为0！");
			reInfo.setCode("-1");
			return reInfo;
		}

		List<TripNode> bustripNodeList = null;
		try {
			bustripNodeList = bustripService.getBusTripNodeList(pageTool);

		} catch (Exception e) {
			reInfo.setMessage("获取列表异常！");
			reInfo.setCode("-1");
			return reInfo;
		}
		if (bustripNodeList.size() == 0) {
			reInfo.setMessage("列表为空！");
			reInfo.setResult(new ArrayList<BustripNode>());
			return reInfo;
		}
		reInfo.setResult(bustripNodeList);
		reInfo.setTotal(totalCount);
		reInfo.setMessage("获取列表成功！");
		return reInfo;
	}
}


