package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Insurance;
import com.yilan.elantrip.domain.rs.RSinsuranceImage;
import com.yilan.elantrip.util.PagingTool;

public interface InsuranceMapper {
    int deleteByPrimaryKey(Integer insuranceId);

    int insert(Insurance record);

    int insertSelective(Insurance record);

    Insurance selectByPrimaryKey(Integer insuranceId);

    int updateByPrimaryKeySelective(Insurance record);

    int updateByPrimaryKey(Insurance record);

	// 查询总数
	int countTotal(PagingTool pagingTool);

	// 查询列表
	List<Insurance> selectInsuranceList(PagingTool pagingTool);

	// 获取详情
	RSinsuranceImage selectInsuranceDetail(int insuranceId);
}