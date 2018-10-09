package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Channel;
import com.yilan.elantrip.util.PagingTool;;

/**
 * 渠道的Service
 * @author Administrator
 *
 */
@Transactional  //开启事务注解,默认运行时异常事务回滚
public interface ChannelService {
	
	/**
	 * 获取渠道详细信息
	 * @param channelId 渠道Id
	 * @return
	 * @throws Exception
	 */
	Object getChannel(Integer channelId)throws Exception;
	
	/**
	 * 添加渠道
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	Object addChannel(Channel channel)throws Exception;
	
	/**
	 * 修改渠道信息
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	Object modifyChannel(Channel channel)throws Exception;
	/**
	 * 获取渠道列表
	 * 
	 * @param PagingTool
	 * @return
	 * @throws Exception
	 */
	List<Channel>getChannelList(PagingTool pagingTool) throws Exception;

	/**
	 * 获取渠道总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool) throws Exception;

}
