package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.ProdTicket;

public interface ProdTicketMapper {
    int deleteByPrimaryKey(Integer incTicketId);

    int insert(ProdTicket record);

    int insertSelective(ProdTicket record);

    ProdTicket selectByPrimaryKey(Integer incTicketId);

    int updateByPrimaryKeySelective(ProdTicket record);

    int updateByPrimaryKey(ProdTicket record);

	List<ProdTicket> selectByProdId(int productId);
}