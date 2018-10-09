package com.yilan.elantrip.domain.rs;


import java.util.List;

import com.yilan.elantrip.domain.InsuranceImage;
/**
 * 用于保险详情
 * @author Administrator
 */
public class RSinsuranceImage {

    private String num; // 编号

    private String title; // 标题

    private String classify; // 分类

    private String type; // 种类

    private String detail; // 详情
    
    private double price; // 价格
    
    private Integer activated; // 状态
    
    private List<InsuranceImage> insuranceImages;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	public List<InsuranceImage> getInsuranceImages() {
		return insuranceImages;
	}

	public void setInsuranceImages(List<InsuranceImage> insuranceImages) {
		this.insuranceImages = insuranceImages;
	}

}