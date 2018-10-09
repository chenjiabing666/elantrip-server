package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdOther;

public interface ProdOtherMapper {
    int deleteByPrimaryKey(Integer incOtherId);

    int insert(ProdOther record);

    int insertSelective(ProdOther record);

    ProdOther selectByPrimaryKey(Integer incOtherId);

    int updateByPrimaryKeySelective(ProdOther record);

    int updateByPrimaryKey(ProdOther record);

}