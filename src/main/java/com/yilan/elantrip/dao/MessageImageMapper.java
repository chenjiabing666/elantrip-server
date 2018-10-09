package com.yilan.elantrip.dao;

import com.yilan.elantrip.domain.MessageImage;

public interface MessageImageMapper {
    int deleteByPrimaryKey(Integer messageImageId);

    int insert(MessageImage record);

    int insertSelective(MessageImage record);

    MessageImage selectByPrimaryKey(Integer messageImageId);

    int updateByPrimaryKeySelective(MessageImage record);

    int updateByPrimaryKey(MessageImage record);
}