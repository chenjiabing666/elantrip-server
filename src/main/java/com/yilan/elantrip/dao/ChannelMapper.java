package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Channel;
import com.yilan.elantrip.util.PagingTool;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer channelId);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer channelId);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
    
	int countTotal(PagingTool pagingTool);
	
	
	List<Channel>selectChannelList(PagingTool pagingTool);
}