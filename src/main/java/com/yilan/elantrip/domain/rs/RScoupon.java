package com.yilan.elantrip.domain.rs;
/**
 * 用于优惠券详情
 * @author Administrator
 */

import java.util.Date;
import java.util.List;

import com.yilan.elantrip.domain.CouponDetails;

public class RScoupon {
   private String couponName; // 名称
   private String couponType; // 类型 
   private double price; //面值
   private int totalCount; // 发放总量
   private int requirment; // 使用条件
   private Date expiryStartDate; // 有效起始时间
   private Date expiryEndDate; //有效截止时间
   private String prodType; // 品类
   private List<CouponDetails> couponDetailsList;
public String getCouponName() {
	return couponName;
}
public void setCouponName(String couponName) {
	this.couponName = couponName;
}
public String getCouponType() {
	return couponType;
}
public void setCouponType(String couponType) {
	this.couponType = couponType;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
public int getRequirment() {
	return requirment;
}
public void setRequirment(int requirment) {
	this.requirment = requirment;
}
public Date getExpiryStartDate() {
	return expiryStartDate;
}
public void setExpiryStartDate(Date expiryStartDate) {
	this.expiryStartDate = expiryStartDate;
}
public Date getExpiryEndDate() {
	return expiryEndDate;
}
public void setExpiryEndDate(Date expiryEndDate) {
	this.expiryEndDate = expiryEndDate;
}
public String getProdType() {
	return prodType;
}
public void setProdType(String prodType) {
	this.prodType = prodType;
}
public List<CouponDetails> getCouponDetails() {
	return couponDetailsList;
}
public void setCouponDetails(List<CouponDetails> couponDetailsList) {
	this.couponDetailsList = couponDetailsList;
} 
   
}
