package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.Department;
import com.yilan.elantrip.util.PagingTool;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectDeptList(PagingTool pagingTool);
    
    int countTotal(PagingTool pagingTool);
    
    List<String> selectDeptNameList();
    
    List<String> selectSubdeptList(Department department);
 }