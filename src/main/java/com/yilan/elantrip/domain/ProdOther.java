package com.yilan.elantrip.domain;

import java.util.Date;

public class ProdOther {
    private Integer otherExplainId;

    private Integer prodId;

    private Integer isAlready;

    private Integer isDiscout;

    private Integer isReply;

    private Integer isHold;

    private String certificateName;

    private Double discount;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getOtherExplainId() {
        return otherExplainId;
    }

    public void setOtherExplainId(Integer otherExplainId) {
        this.otherExplainId = otherExplainId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getIsAlready() {
        return isAlready;
    }

    public void setIsAlready(Integer isAlready) {
        this.isAlready = isAlready;
    }

    public Integer getIsDiscout() {
        return isDiscout;
    }

    public void setIsDiscout(Integer isDiscout) {
        this.isDiscout = isDiscout;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Integer getIsHold() {
        return isHold;
    }

    public void setIsHold(Integer isHold) {
        this.isHold = isHold;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName == null ? null : certificateName.trim();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}