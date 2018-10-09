package com.yilan.elantrip.service;

import java.util.List;

import com.yilan.elantrip.domain.Bustrip;
import com.yilan.elantrip.domain.BustripNode;
import com.yilan.elantrip.domain.BustripStock;
import com.yilan.elantrip.domain.TripNode;
import com.yilan.elantrip.util.PagingTool;

public interface BustripService {
	
	Object modifyBustrip(Bustrip bustrip) throws Exception;
	
	Object addBustripNode(List<BustripNode> bustripNode) throws Exception;
	
	Object modifyNode(TripNode tripNode) throws Exception;
	
	Object modifyBustripStock(List<BustripStock> bustripStocks) throws Exception;
	
	Object countTotal(PagingTool pagingTool) throws Exception;
	
	Object countNodeTotal(PagingTool pagingTool) throws Exception;
	
	List<Bustrip> getBusTripList(PagingTool pagingTool) throws Exception;
	
	List<TripNode> getBusTripNodeList(PagingTool pagingTool) throws Exception;
	
	Object getBustripById(Short tripId) throws Exception;
}
