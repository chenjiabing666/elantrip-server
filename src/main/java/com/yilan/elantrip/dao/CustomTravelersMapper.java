package com.yilan.elantrip.dao;

import java.util.List;
import java.util.Map;

import com.yilan.elantrip.domain.CustomTravelers;
import com.yilan.elantrip.domain.rs.Traveler;

public interface CustomTravelersMapper {
    int deleteByPrimaryKey(Integer travelerId);

    int insert(CustomTravelers record);

    int insertSelective(CustomTravelers record);

    CustomTravelers selectByPrimaryKey(Integer travelerId);

    int updateByPrimaryKeySelective(CustomTravelers record);

    int updateByPrimaryKey(CustomTravelers record);
    
    int placeOrderTrav(List<Traveler> travelerList);

}