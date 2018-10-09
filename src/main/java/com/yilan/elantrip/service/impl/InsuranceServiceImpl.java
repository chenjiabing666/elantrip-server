package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.InsuranceImageMapper;
import com.yilan.elantrip.dao.InsuranceMapper;
import com.yilan.elantrip.domain.Insurance;
import com.yilan.elantrip.domain.InsuranceImage;
import com.yilan.elantrip.domain.rs.RSinsuranceImage;
import com.yilan.elantrip.service.InsuranceService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Resource
	private InsuranceMapper insuranceMapper;
	@Resource
	private InsuranceImageMapper insuranceImageMapper;

	@Override
	public Object addInsurance(Insurance insurance, List<InsuranceImage> insuranceImages) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 插入消息
		int count = insuranceMapper.insertSelective(insurance);
		if (count == 0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		// 插入图片
		for (InsuranceImage insuranceImage : insuranceImages) {
			insuranceImage.setInsuranceId(insurance.getInsuranceId());
			int count1 = insuranceImageMapper.insertSelective(insuranceImage);
			if (count1 == 0) {
				resultInfo.setCode("-1");
				resultInfo.setMessage("添加图片失败");
				return resultInfo;
			}
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object modifyInsurance(Insurance insurance) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = insuranceMapper.updateByPrimaryKeySelective(insurance);
			if (count < 0) {
				resultInfo.setCode("-1");
				throw new Exception("修改新闻失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("修改新闻异常");
		}
		return count;
	}

	@Override
	public Object getInsuranceById(int insuranceId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		RSinsuranceImage rSinsuranceImage = null;
		try {
			rSinsuranceImage = insuranceMapper.selectInsuranceDetail(insuranceId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取详情异常");
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(rSinsuranceImage);
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = insuranceMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}

	@Override
	public List<Insurance> getInsuranceList(PagingTool pagingTool) throws Exception {
		List<Insurance> insuranceList = null;
		try {
			insuranceList = insuranceMapper.selectInsuranceList(pagingTool);
		} catch (Exception e) {
			throw new ApplicationContextException("获取新闻列表异常");
		}

		return insuranceList;
	}

}
