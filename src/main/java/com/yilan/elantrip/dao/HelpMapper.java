package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Help;
import com.yilan.elantrip.util.PagingTool;


public interface HelpMapper {
    int deleteByPrimaryKey(Integer helpId);

    int insert(Help record);

    int insertSelective(Help record);

    Help selectByPrimaryKey(Integer helpId);

    int updateByPrimaryKeySelective(Help record);

    int updateByPrimaryKey(Help record);
    
    List<Help> getHelpList(PagingTool pagingTool);
    
    int countTotal(PagingTool pagingTool);

}