package com.yilan.elantrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.ChannelMapper;
import com.yilan.elantrip.domain.Channel;
import com.yilan.elantrip.service.ChannelService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class ChannelServiceImpl implements ChannelService  {

	@Resource
	private ChannelMapper channelMapper;  //注入dao
	
	@Override
	public Object getChannel(Integer channelId) throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		Channel channel=channelMapper.selectByPrimaryKey(channelId);
		if (channel==null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("渠道不存在");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(channel);
		return resultInfo;
	}

	@Override
	public Object addChannel(Channel channel) throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		int count=channelMapper.insertSelective(channel); 
		
		if (count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object modifyChannel(Channel channel) throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		int count=channelMapper.updateByPrimaryKeySelective(channel);
		
		if (count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改失败");
			return resultInfo;
		}
		
		resultInfo.setMessage("修改成功");
		resultInfo.setCode("0");
		return resultInfo;
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = channelMapper.countTotal(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取总数异常");
		}
		return count;
	}
	
	@Override
	public List<Channel> getChannelList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<Channel> channelList = null;
		try {
			channelList = channelMapper.selectChannelList(pagingTool);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setCode("-1");
			throw new ApplicationContextException("获取渠道列表异常");
		}

		return channelList;
	}
	
	
}
