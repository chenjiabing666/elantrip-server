package com.yilan.elantrip.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import com.yilan.elantrip.dao.ProdBrightMapper;
import com.yilan.elantrip.dao.ProdCarMapper;
import com.yilan.elantrip.dao.ProdDescImageMapper;
import com.yilan.elantrip.dao.ProdIncOtherMapper;
import com.yilan.elantrip.dao.ProdIntroImageMapper;
import com.yilan.elantrip.dao.ProdIntroMapper;
import com.yilan.elantrip.dao.ProdLanguageMapper;
import com.yilan.elantrip.dao.ProdAccMapper;
import com.yilan.elantrip.dao.ProdAttentionDangerMapper;
import com.yilan.elantrip.dao.ProdAttentionOtherMapper;
import com.yilan.elantrip.dao.ProdAttentionRelayMapper;
import com.yilan.elantrip.dao.ProdAttentionRemindMapper;
import com.yilan.elantrip.dao.ProdAttentionShopMapper;
import com.yilan.elantrip.dao.ProdAttentionTimeMapper;
import com.yilan.elantrip.dao.ProdMapper;
import com.yilan.elantrip.dao.ProdOtherMapper;
import com.yilan.elantrip.dao.ProdPinfoMapper;
import com.yilan.elantrip.dao.ProdPrimaryImageMapper;
import com.yilan.elantrip.dao.ProdRestaurantMapper;
import com.yilan.elantrip.dao.ProdStockMapper;
import com.yilan.elantrip.dao.ProdTicketMapper;
import com.yilan.elantrip.dao.ProdTrafficMapper;
import com.yilan.elantrip.dao.ProdUseageMapper;
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
import com.yilan.elantrip.dao.ProdVideoMapper;
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

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProdMapper productMapper;
	@Resource
	private ProdPrimaryImageMapper prodPrimaryImageMapper;
	@Resource
	private ProdVideoMapper prodVideoMapper;
	@Resource
	private ProdIntroMapper prodIntroMapper;
	@Resource
	private ProdIntroImageMapper prodIntroImageMapper;
	@Resource
	private ProdUseageMapper prodUseageMapper;
	@Resource
	private ProdAttentionDangerMapper ProdAttentionDangerMapper;
	@Resource
	private ProdAttentionOtherMapper prodAttentionOtherMapper;
	@Resource
	private ProdAttentionRelayMapper prodAttentionRelayMapper;
	@Resource
	private ProdAttentionRemindMapper prodAttentionRemindMapper;
	@Resource
	private ProdAttentionShopMapper prodAttentionShopMapper;
	@Resource
	private ProdAttentionTimeMapper prodAttentionTimeMapper;
	@Resource
	private ProdPinfoMapper prodPinfoMapper;
	@Resource
	private ProdOtherMapper prodOtherMapper;
	@Resource
	private ProdDescImageMapper prodDescImageMapper;
	@Resource
	private ProdBrightMapper prodBrightMapper;
	@Resource
	private ProdCarMapper prodCarMapper;
	@Resource
	private ProdStockMapper prodStockMapper;
    @Resource
    private ProdAccMapper prodAccMapper;
	@Resource
	private ProdLanguageMapper prodLanguageMapper;
	@Resource
	private ProdRestaurantMapper prodRestaurantMapper;
	@Resource
	private ProdTicketMapper prodTicketMapper;
	@Resource
	private ProdTrafficMapper prodTrafficMapper;
	@Resource
	private ProdIncOtherMapper prodIncOtherMapper;
	@Override
	public Object addProductBasic(Prod product, ProdPrimaryImage primaryImage, ProdVideo prodVideo) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 插入产品
		int count = productMapper.insertSelective(product);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加产品失败");
			return resultInfo;
		}
		primaryImage.setProdId(product.getProdId());
		primaryImage.setCreatedDate(new Date());
		int count1 = prodPrimaryImageMapper.insertSelective(primaryImage);
		if (count1 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加图片失败");
			return resultInfo;
		}
		prodVideo.setProdId(product.getProdId());
		prodVideo.setCreatedDate(new Date());
		int count2 = prodVideoMapper.insertSelective(prodVideo);
		if (count2 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加视频失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object addProdIntro(List<ProdIntroImage> introImages, List<ProdIntro> productIntro) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 插入产品
		int count = prodIntroMapper.addProdIntro(productIntro);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		// 插入图片
			int count1 = prodIntroImageMapper.addProdIntroImage(introImages);
			if (count1 == 0) {
				throw new RuntimeException();
			}
		
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object modifyProduct(Prod product) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = productMapper.updateByPrimaryKey(product);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("修改产品失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("修改新闻异常");
		}
		return count;
	}

	@Override
	public Object getProductById(int productId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Map<Object, Object> result = new HashMap<>();
		 
		try {
			Prod prod = productMapper.selectByPrimaryKey(productId);
			result.put("prod", prod);
			ProdPrimaryImage primaryImage = prodPrimaryImageMapper.selectByProdId(productId);
			result.put("primaryImage", primaryImage);
			List<ProdStock> prodStocks = prodStockMapper.selectByProdId(productId);
			result.put("prodStocks", prodStocks);
			List<ProdIntro> productIntros = prodIntroMapper.selectByProdId(productId);
			result.put("productIntros", productIntros);
			List<ProdIntroImage> productIntroImages = prodIntroImageMapper.selectByProdId(productId);
			result.put("productIntroImages", productIntroImages);
			List<ProdAcc> prodAccs = prodAccMapper.selectByProdId(productId);
			result.put("prodAccs", prodAccs);
			List<ProdLanguage> prodLanguages = prodLanguageMapper.selectByProdId(productId);
			result.put("prodLanguages", prodLanguages);
			List<ProdIncOther> prodIncOthers = prodIncOtherMapper.selectByProdId(productId);
			result.put("prodIncOthers", prodIncOthers);
			List<ProdRestaurant> prodRestaurants = prodRestaurantMapper.selectByProdId(productId);
			result.put("prodRestaurants", prodRestaurants);
			List<ProdTicket> prodTickets = prodTicketMapper.selectByProdId(productId);
			result.put("prodTickets", prodTickets);
			List<ProdTraffic> prodTraffics = prodTrafficMapper.selectByProdId(productId);
			result.put("prodTraffics", prodTraffics);
			List<ProdDescImage> pDescImages = prodDescImageMapper.selectByProdId(productId);
			result.put("pDescImages", pDescImages);
			List<ProdBright>prodBrights = prodBrightMapper.selectByProdId(productId);
			result.put("prodBrights", prodBrights);
			
			resultInfo.setCode("0");
			resultInfo.setMessage("获取成功");
			resultInfo.setResult(result);
			return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}
		
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = productMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<Prod> getProductList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<Prod> productList = null;
		try {
			productList = productMapper.selectProductList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取产品列表异常");
		}

		return productList;
	}

	@Override
	public Object addProdBright(List<ProdDescImage> descImages, ProdBright prodBright) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 插入消息
		int count = prodBrightMapper.insertSelective(prodBright);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加产品亮点失败");
			return resultInfo;
		}
		// 插入图片
		for (ProdDescImage descImage : descImages) {
			descImage.setProdId(prodBright.getProdId());
			descImage.setCreatedDate(new Date());
			int count1 = prodDescImageMapper.insertSelective(descImage);
			if (count1 == 0) {
				throw new RuntimeException();
			}
		}
		resultInfo.setMessage("添加产品描述成功");
		return resultInfo;
	}

	@Override
	public Object addProdUseAge(ProdUseage prodUseage) {
		ResultInfo resultInfo = new ResultInfo();
		// 插入使用方法
		int count = prodUseageMapper.insertSelective(prodUseage);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加使用方法失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加使用方法成功");
		return resultInfo;
	}

	@Override
	public Object addProdAttention(List<ProdAttentionRelay> par, List<ProdAttentionShop> pas,
			List<ProdAttentionRemind> pare, List<ProdAttentionDanger> pad, List<ProdAttentionOther> pao,
			List<ProdAttentionTime> pat) {
		ResultInfo resultInfo = new ResultInfo();
		// 插入接送/集合
		int count = prodAttentionRelayMapper.insertBatchRelay(par);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加集合失败");
			return resultInfo;
		}
		// 插入购物
		int count1 = prodAttentionShopMapper.insertBatchShap(pas);
		if (count1 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加购物失败");
			return resultInfo;
		}
		// 插入温馨提示
		int count2 = prodAttentionRemindMapper.insertBatchRemind(pare);
		if (count2 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加温馨提示失败");
			return resultInfo;
		}
		// 插入安全警告
		int count3 = ProdAttentionDangerMapper.insertBatchDanger(pad);
		if (count3 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加安全警告失败");
			return resultInfo;
		}
		// 插入其他
		int count4 = prodAttentionOtherMapper.insertBatchOther(pao);
		if (count4 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加其他失败");
			return resultInfo;
		}
		// 插入时令公告
		int count5 = prodAttentionTimeMapper.insertBatchTime(pat);
		if (count5 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加时令公告失败");
			return resultInfo;
		}
		resultInfo.setMessage("添加注意事项成功");
		return resultInfo;
	}

	@Override
	public Object addProdPersonInfo(ProdPinfo pinfo, ProdOther prodOther) {
		ResultInfo resultInfo = new ResultInfo();
		// 插入接送/集合
		int count = prodPinfoMapper.insert(pinfo);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加人群信息失败");
			return resultInfo;
		}
		// 插入购物
		int count1 = prodOtherMapper.insert(prodOther);
		if (count1 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加其他失败");
			return resultInfo;
		}
		resultInfo.setMessage("添加其他成功");
		return resultInfo;
	}

	@Override
	public Object addProdExplain(List<ProdTicket> prodTicket, List<ProdTraffic> prodTraffic, List<ProdAcc> prodAcc,
			List<ProdRestaurant> prodRestaurant, List<ProdIncOther> prodOthersList) throws Exception {
		
		return null;
	}

	@Override
	public Object addProdStock(ProdCar prodCar, List<ProdStock> prodStocks) {
		ResultInfo resultInfo = new ResultInfo();
		// 插入包车
		if (prodCar != null) {
			int count = prodCarMapper.insertSelective(prodCar);
			if (count == 0) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("添加包车失败");
				return resultInfo;
			}
		}
		// 插入库存		
		int count1 = prodStockMapper.insertBatchStock(prodStocks);
		if (count1 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加库存失败");
			return resultInfo;
		}
		Prod prod = new Prod();
		prod.setProdId(prodCar.getProdId());
		prod.setStock(prodStocks.size());
		int count2 = productMapper.updateByPrimaryKeySelective(prod);
		if (count2 == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加库存失败");
			return resultInfo;
		}
		resultInfo.setResult(count1);
		resultInfo.setMessage("添加库存成功");
		return resultInfo;
	}
}
