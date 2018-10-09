package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.ProdAcc;
import com.yilan.elantrip.domain.ProdBright;
import com.yilan.elantrip.domain.ProdCar;
import com.yilan.elantrip.domain.ProdDescImage;
import com.yilan.elantrip.domain.ProdIncOther;
import com.yilan.elantrip.domain.ProdIntro;
import com.yilan.elantrip.domain.ProdIntroImage;
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
import com.yilan.elantrip.util.PagingTool;

/**
 * 产品的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface ProductService {

	/**
	 * 添加产品基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProductBasic(Prod product,ProdPrimaryImage primaryImage,ProdVideo prodVideo)throws Exception;
	
	/**
	 * 添加产品行程介绍
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProdIntro(List<ProdIntroImage> introImages, List<ProdIntro> prodIntros) throws Exception;
	/**
	 * 添加产品购买须知下的使用方法
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProdUseAge(ProdUseage prodUseage);
	/**
	 * 添加产品购买须知下的注意事项
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProdAttention(List<ProdAttentionRelay> par,List<ProdAttentionShop> pas,List<ProdAttentionRemind> pare,List<ProdAttentionDanger> pad,List<ProdAttentionOther> pao,List<ProdAttentionTime> pat);
	/**
	 * 添加产品人群信息
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProdPersonInfo(ProdPinfo pinfo,ProdOther prodOther);
	/**
	 * 添加产品库存
	 * 
	 * @return
	 * @throws Exception
	 */
	Object addProdStock(ProdCar prodCar,List<ProdStock> prodStocks);
	/**
	 * 修改产品
	 * 
	 * @return
	 * @throws Exception
	 */
	Object modifyProduct(Prod product)throws Exception;

	/**
	 * 获取产品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	Object getProductById(int productId)throws Exception;

	/**
	 * 获取产品总数
	 * 
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Prod> getProductList(PagingTool pagingTool)throws Exception;
	
	/**
	 * 添加产品描述和亮点
	 * @param descImages
	 * @param prodBright
	 * @return
	 * @throws Exception
	 */
	Object addProdBright(List<ProdDescImage> descImages, ProdBright prodBright) throws Exception;
	/**
	 * 添加产品说明
	 * @param prodTicket
	 * @param prodTraffic
	 * @param prodAcc
	 * @param prodRestaurant
	 * @param prodOthersList
	 * @return
	 * @throws Exception
	 */
	Object addProdExplain(
		List<ProdTicket> prodTicket,
		List<ProdTraffic> prodTraffic,
		List<ProdAcc> prodAcc,
		List<ProdRestaurant> prodRestaurant,
		List<ProdIncOther> prodOthersList) throws Exception;
}
