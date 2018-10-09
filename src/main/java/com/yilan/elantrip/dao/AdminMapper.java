package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Admin;
import com.yilan.elantrip.util.PagingTool;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    int countTotal(PagingTool pagingTool);
    
    List<Admin> selectAdmintList(PagingTool pagingTool);
    
  }