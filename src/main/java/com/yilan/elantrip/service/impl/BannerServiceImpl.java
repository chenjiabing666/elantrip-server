package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.BannerMapper;
import com.yilan.elantrip.domain.Banner;
import com.yilan.elantrip.domain.Rate;
import com.yilan.elantrip.service.BannerService;
import com.yilan.elantrip.util.PagingTool;

@Service("BannerService")
public class BannerServiceImpl implements BannerService {
    
	@Resource
	private BannerMapper bannerMapper;//注入dao

	public BannerMapper getBannerMapper() {
		return bannerMapper;
	}

	public void setBannerMapper(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}
    //添加广告
	
	@Override
	public Integer addBanner(Banner banner) {
		int count = 0;
		try {
			count = bannerMapper.insertSelective(banner);
			if (count < 0) {
				throw new Exception("添加广告失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("添加广告异常！");
		}
		return count;
	}
    
	//删除广告
	@Override
	public Integer delBanner(int bannerId) {
		int count = 0;
		try {
			count = bannerMapper.deleteByPrimaryKey(bannerId);
			if (count < 0) {
				throw new Exception("删除广告失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("删除广告异常！");
		}
		return count;
	}
    
	//修改广告
	@Override
	public Object modifyBanner(Banner banner) {
		System.out.println("1111"+banner);
		int count = 0;
		try {
			count = bannerMapper.updateByPrimaryKeySelective(banner);
			if (count < 0) {
				throw new Exception("修改广告失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("修改广告异常！");
		}
		return count;
	}
    
	//获取广告
	//@JsonIgnoreProperties(value={""})   
	@Override
	public Banner getBannerByBannerId(int bannerId) {
		Banner banner = null;
		try {
			banner = bannerMapper.selectByPrimaryKey(bannerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取广告详情异常！");
		}
		return banner;
	}

	// 获取广告总数
	@Override
	public Integer countTotal(PagingTool pagingTool) {
		int count = 0;

		try {
			count = bannerMapper.countTotal(pagingTool);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取广告总数异常！");
		}
		return count;
	}
    //获取广告列表
	@Override
	public List<Banner> getBannerList(PagingTool pagingTool) {
		List<Banner> bannerList = null;

		try {
			bannerList = bannerMapper.selectBannerList(pagingTool);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取广告列表异常");
		}
		return bannerList;
	}

	@Override
	public Integer deleteBatch(String[] idArr) {
		// TODO Auto-generated method stub
		return 0;
	}

}
