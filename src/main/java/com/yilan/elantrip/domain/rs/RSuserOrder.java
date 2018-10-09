package com.yilan.elantrip.domain.rs;

import java.util.Date;

/*
 * 用于显示用户详情中的订单列表
 */
public class RSuserOrder {
   private String orderCode; // 订单编号
   private String prodTitle; // 产品名称
   private String userName; // 用户名
   private String prodType; //品类
   private String contactPerson; // 联系人
   private double price; // 价格
   private String fromLocation; // 出发点
   private Date createdDate; // 创建时间
   private String discount; // 优惠级别
   private String remark; // 备注
   private Integer activated; // 状态
   
public String getDiscount() {
	return discount;
}
public void setDiscount(String discount) {
	this.discount = discount;
}
public String getOrderCode() {
	return orderCode;
}
public void setOrderCode(String orderCode) {
	this.orderCode = orderCode;
}
public String getProdTitle() {
	return prodTitle;
}
public void setProdTitle(String prodTitle) {
	this.prodTitle = prodTitle;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getProdType() {
	return prodType;
}
public void setProdType(String prodType) {
	this.prodType = prodType;
}
public String getContactPerson() {
	return contactPerson;
}
public void setContactPerson(String contactPerson) {
	this.contactPerson = contactPerson;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getFromLocation() {
	return fromLocation;
}
public void setFromLocation(String fromLocation) {
	this.fromLocation = fromLocation;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Integer getActivated() {
	return activated;
}
public void setActivated(Integer activated) {
	this.activated = activated;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
@Override
public String toString() {
	return "RSuserOrder [orderCode=" + orderCode + ", prodTitle=" + prodTitle + ", userName=" + userName + ", prodType="
			+ prodType + ", contactPerson=" + contactPerson + ", price=" + price + ", fromLocation=" + fromLocation
			+ ", createdDate=" + createdDate + ", activated=" + activated + ", discount=" + discount + ", remark="
			+ remark + "]";
}
   
   
}
