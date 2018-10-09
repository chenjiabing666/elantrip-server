package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.JourneyListMouldMapper;
import com.yilan.elantrip.domain.JourneyListMould;
import com.yilan.elantrip.service.JourneyListMouldService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class JourneyListMouldServiceImpl implements JourneyListMouldService {

	@Resource
	private JourneyListMouldMapper journeyListMouldMapper;

	@Override
	public Object addJourneyListMould(JourneyListMould journeyListMould) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = journeyListMouldMapper.insertSelective(journeyListMould);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		resultInfo.setResult(count);
		return resultInfo;
	}

	@Override
	public Object getJourneyListMouldById(int journeyListMouldId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		System.out.println(journeyListMouldId);
		JourneyListMould journeyListMould = null;
		try {
			journeyListMould = journeyListMouldMapper.selectByPrimaryKey(journeyListMouldId);
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取失败");
			return resultInfo;
		}
        resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(journeyListMould);
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = journeyListMouldMapper.countTotal(pagingTool);
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取总数失败");
			return resultInfo;
		}
		return count;
	}

	@Override
	public List<JourneyListMould>  getJourneyListMouldList(PagingTool pagingTool) throws Exception {
		List<JourneyListMould> journeyListMouldList = null;
		try {
			journeyListMouldList = journeyListMouldMapper.selectJourneyListMouldList(pagingTool);
		} catch (Exception e) {
			throw new ApplicationContextException("获取列表失败");
		}
		return journeyListMouldList;
	}

	@Override
	public Object DelJourneyListMouldById(int journeyListMouldId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = journeyListMouldMapper.deleteByPrimaryKey(journeyListMouldId);
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("删除失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("删除成功");
		resultInfo.setResult(count);
		return resultInfo;
	}

	@Override
	public Object updateJourneyListMould(JourneyListMould journeyListMould) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = journeyListMouldMapper.updateByPrimaryKeySelective(journeyListMould);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("修改成功");
		resultInfo.setResult(count);
		return resultInfo;
	}
   
	
}
