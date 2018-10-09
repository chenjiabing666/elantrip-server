package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.util.PagingTool;

@Transactional  //开启事务注解,默认运行时异常事务回滚
public interface BannerService {

	// 增删改查
	/**
	 * 添加广告
	 * @param banner 广告
	 * @return
	 * @throws Exception
	 */
	Integer addBanner(Banner banner)throws Exception;
	/**
	 * 删除广告
	 * @param bannerID 广告Id
	 * @return
	 * @throws Exception
	 */
	Integer delBanner(int bannerId)throws Exception;
	/**
	 * 编辑广告
	 * @param banner 广告
	 * @return
	 * @throws Exception
	 */
	Object modifyBanner(Banner banner)throws Exception;
	/**
	 * 获取广告信息
	 * @param bannerID 广告Id
	 * @return
	 * @throws Exception
	 */
	
	Banner getBannerByBannerId(int bannerId)throws Exception;
	/**
	 * 获取广告总数
	 * @param userId 用户Id
	 * @return
	 * @throws Exception
	 */
	Integer countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取广告列表
	 * @param pagingtool 
	 * @return
	 * @throws Exception
	 */
	List<Banner> getBannerList(PagingTool pagingTool)throws Exception;
	/**
	 * 批量删除
	 * @param idArr 广告数组
	 * @return
	 * @throws Exception
	 */	
	Integer deleteBatch(String[] idArr)throws Exception;
}
