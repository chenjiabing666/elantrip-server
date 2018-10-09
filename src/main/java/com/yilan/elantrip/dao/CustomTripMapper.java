package com.yilan.elantrip.dao;

import java.util.List;

import com.yilan.elantrip.domain.CustomTrip;
import com.yilan.elantrip.domain.rs.RScustomeTrip;
import com.yilan.elantrip.util.PagingTool;

public interface CustomTripMapper {
    int deleteByPrimaryKey(Integer customAppId);

    int insert(CustomTrip record);

    int insertSelective(CustomTrip record);

    CustomTrip selectByPrimaryKey(Integer customAppId);

    int updateByPrimaryKeySelective(CustomTrip record);

    int updateByPrimaryKey(CustomTrip record);
    
    int addTripOne (CustomTrip customTrip);
    
    int addTripTwo (CustomTrip customTrip);

    int addTripThree (CustomTrip customTrip);
        
    int placeOrder(CustomTrip customTrip);
    
    int countTotal(PagingTool pagingTool);
    
    List<RScustomeTrip> getCusTripList(PagingTool pagingTool);

}