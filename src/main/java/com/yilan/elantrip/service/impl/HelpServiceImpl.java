package com.yilan.elantrip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.HelpImageMapper;
import com.yilan.elantrip.dao.HelpMapper;
import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.domain.HelpImage;
import com.yilan.elantrip.service.HelpService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;
@Service("HelpService")
public class HelpServiceImpl implements HelpService {
    
	@Resource
	private HelpImageMapper helpImageMapper;
	@Resource
	private HelpMapper helpMapper;

	
	
	public HelpImageMapper getHelpImageMapper() {
		return helpImageMapper;
	}

	public void setHelpImageMapper(HelpImageMapper helpImageMapper) {
		this.helpImageMapper = helpImageMapper;
	}

	public HelpMapper getHelpMapper() {
		return helpMapper;
	}

	public void setHelpMapper(HelpMapper helpMapper) {
		this.helpMapper = helpMapper;
	}
	/**
	 * 获取问题BY helpId
	 * @param helpId
	 */
	@Override
	public Object getHelp(Integer helpId) throws Exception {
        Help help = null;
        try {
        help = helpMapper.selectByPrimaryKey(helpId);
        }
        catch (Exception e) {
        throw new Exception("获取问题错误");
        }
		return help;
	}
	
	/**
	 * 获取问题列表
	 * @param helpId
	 */
	@Override
	public List<Help> getHelpList(PagingTool pagingTool) throws Exception {
        List<Help> hList = null;
        try {
        hList = helpMapper.getHelpList(pagingTool);
        }
        catch (Exception e) {
        throw new Exception("获取问题列表错误");
        }
		return hList;
	}
    
	
	/**
	 * 添加问题
	 * @param Help, List<HelpImage>
	 */
	@Override
	public Object addHelp(Help help, List<HelpImage> images) throws Exception {
		List<HelpImage> helpImages = new ArrayList<HelpImage>();
		ResultInfo rsInfo = new ResultInfo();
        //添加问题和答案
		int count1 = helpMapper.insertSelective(help);
		if (count1 == 0) {
			rsInfo.setCode("-1");
			rsInfo.setMessage("添加失败");
			return rsInfo;
		}
		//添加问题图片
//		for (HelpImage helpImage : images) {
//		helpImage.setHelpId(help.getHelpId());
//		helpImage.setCreatedDate(new Date());
//		}
//		
//		int count2 = helpImageMapper.addImages(helpImages);
//		if (count2 == 0) {
//			rsInfo.setCode("-1");
//			rsInfo.setMessage("添加失败");
//			return rsInfo;
//		}
		return rsInfo;
	}
	
    /**
     * 删除问题
     * @param helpID 问题编号
     */
	@Override
	public Integer deleteHelp(Integer helpId) throws Exception {
		int count = 0;
		try {
			count = helpMapper.deleteByPrimaryKey(helpId);
			if (count < 0) {
				throw new Exception("删除问题失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("删除问题异常！");
		}
		return count;
	}
    /**
     * 获取问题总数
     */
	@Override
	public Integer countTotal(PagingTool pagingTool) {
		int count = 0;

		try {
			count = helpMapper.countTotal(pagingTool);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("获取总数问题异常！");
		}
		return count;
	}
}
