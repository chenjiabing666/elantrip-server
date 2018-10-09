package com.yilan.elantrip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yilan.elantrip.domain.UserLinkTravller;
import com.yilan.elantrip.util.PagingTool;

public interface UserLinkTravllerMapper {
	int deleteByPrimaryKey(Integer userLinkTravllerId);

	int insert(UserLinkTravller record);

	int insertSelective(UserLinkTravller record);

	UserLinkTravller selectByPrimaryKey(Integer userLinkTravllerId);

	int updateByPrimaryKeySelective(UserLinkTravller record);

	int updateByPrimaryKey(UserLinkTravller record);

	int insertBatchUserLinkTravller(@Param("userLinkTravllerList") List<UserLinkTravller> prodAttentionTimes);

	int countTotal(PagingTool pagingTool);

	List<UserLinkTravller> selectUserLinkList(PagingTool pagingTool);
}