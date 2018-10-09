package com.yilan.elantrip.domain;

public class Rate {
    private Integer rateId;

    private Double rate;

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

	@Override
	public String toString() {
		return "Rate [rateId=" + rateId + ", rate=" + rate + "]";
	}
    
}