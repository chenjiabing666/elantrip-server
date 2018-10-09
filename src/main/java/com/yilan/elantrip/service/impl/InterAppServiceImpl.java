package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.InterAppMapper;
import com.yilan.elantrip.domain.InterApp;
import com.yilan.elantrip.service.InterAppService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class InterAppServiceImpl implements InterAppService{
    
	@Resource
	InterAppMapper interAppMapper;
	
	@Override
	public Object addInterApp(InterApp interApp) throws Exception {
	    ResultInfo resultInfo = new ResultInfo();
		int count = interAppMapper.insertSelective(interApp);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
	    resultInfo.setMessage("添加成功");
		return null;
	}
	
	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = interAppMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<InterApp> getInterList(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<InterApp> InterList = null;
		try {
			InterList = interAppMapper.getInterList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取列表异常");
		}

		return InterList;
	}


}
