package com.yilan.elantrip.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yilan.elantrip.dao.BustripMapper;
import com.yilan.elantrip.dao.BustripNodeMapper;
import com.yilan.elantrip.dao.BustripStockMapper;
import com.yilan.elantrip.dao.TripNodeMapper;
import com.yilan.elantrip.domain.Bustrip;
import com.yilan.elantrip.domain.BustripNode;
import com.yilan.elantrip.domain.BustripStock;
import com.yilan.elantrip.domain.TripNode;
import com.yilan.elantrip.service.BustripService;
import com.yilan.elantrip.util.PagingTool;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class BustripServiceImpl implements BustripService{
	
	@Resource
	private BustripMapper bustripMapper;
	@Resource
	private BustripNodeMapper BustripNodeMapper;
	@Resource
	private TripNodeMapper tripNodeMapper;
	@Resource
	private BustripStockMapper bustripStockMapper;
	
	
	
	

	@Override
	public Object modifyBustrip(Bustrip bustrip) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		int count = bustripMapper.updateByPrimaryKey(bustrip);
		if(count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改巴士环游详情失败");
		}else {
			resultInfo.setCode("0");
			resultInfo.setMessage("修改成功");
		}
		return resultInfo;
	}
		
	

	@Override
	public Object addBustripNode(List<BustripNode> bustripNode) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = BustripNodeMapper.addBustripNodes(bustripNode);
		} catch (Exception e) {
			resultInfo.setMessage("添加节点失败");
			resultInfo.setCode("-1");
			throw new Exception("添加节点失败");
		}
		return count;
	}

	@Override
	public Object modifyNode(TripNode tripNode) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = tripNodeMapper.updateByPrimaryKeySelective(tripNode);
		} catch (Exception e) {
			resultInfo.setMessage("修改节点失败");
			resultInfo.setCode("-1");
			throw new Exception("修改节点失败");
		}
		return count;
	}
	

	@Override
	public Object modifyBustripStock(List<BustripStock> bustripStocks) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = bustripStockMapper.modifyBustripStock(bustripStocks);
		} catch (Exception e) {
			resultInfo.setMessage("修改库存失败");
			resultInfo.setCode("-1");
			throw new Exception("修改库存失败");
		}
		return count;
	
	}

	@Override
	public Object countTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = bustripMapper.countTotal(pagingTool);
		} catch (Exception e) {
			resultInfo.setMessage("获取环线总数失败");
			resultInfo.setCode("-1");
			throw new Exception("获取环线总数异常");
		}
		return count;
	}

	@Override
	public Object countNodeTotal(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		int count = 0;
		try {
			count = tripNodeMapper.countNodeTotal(pagingTool);
		} catch (Exception e) {
			resultInfo.setMessage("获取节点总数失败");
			resultInfo.setCode("-1");
			throw new Exception("获取节点总数异常");
		}
		return count;
	}

	@Override
	public List<Bustrip> getBusTripList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<Bustrip> bustrips = null;
		try {
			bustrips = bustripMapper.getBustripList(pagingTool);
		} catch (Exception e) {
			resultInfo.setMessage("获取列表异常");
		}
		return bustrips;
	}

	@Override
	public List<TripNode> getBusTripNodeList(PagingTool pagingTool) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		List<TripNode> tripNodes = null;
		try {
			tripNodes = tripNodeMapper.getBustripNodeList(pagingTool);
		} catch (Exception e) {
			resultInfo.setMessage("获取列表异常");
		}
		return tripNodes;
	}

	@Override
	public Object getBustripById(Short tripId) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Bustrip bustrip = null;
		try {
	        bustripMapper.selectByPrimaryKey(tripId);
		} catch (Exception e) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("获取详情错误");
		}
		return bustrip;

	}

}
