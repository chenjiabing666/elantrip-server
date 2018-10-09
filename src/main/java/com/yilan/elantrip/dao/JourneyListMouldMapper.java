package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.JourneyListMould;
import com.yilan.elantrip.util.PagingTool;

public interface JourneyListMouldMapper {
    int deleteByPrimaryKey(Integer journeyListMouldId);

    int insert(JourneyListMould record);

    int insertSelective(JourneyListMould record);

    JourneyListMould selectByPrimaryKey(Integer journeyListMouldId);

    int updateByPrimaryKeySelective(JourneyListMould record);

    int updateByPrimaryKey(JourneyListMould record);

	int countTotal(PagingTool pagingTool);

	List<JourneyListMould> selectJourneyListMouldList(PagingTool pagingTool);
}