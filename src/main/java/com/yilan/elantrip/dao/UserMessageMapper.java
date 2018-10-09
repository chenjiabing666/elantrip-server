package com.yilan.elantrip.dao;

import java.util.ArrayList;
import java.util.List;

import com.yilan.elantrip.domain.UserMessage;

public interface UserMessageMapper {
    int deleteByPrimaryKey(Integer userMessageId);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(Integer userMessageId);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);
    
    String[] getUserIdByUserType(String userType);
    
    int insertBatchUserMessage(ArrayList<UserMessage> userMessages);
    
    // 根据消息id查找需要发送短信的用户电话
    String  getMobilesByUserIds(List<String> userIds);
}