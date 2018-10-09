package com.yilan.elantrip.domain;

import java.util.Date;

public class ProdAttentionDanger {
    private Integer attentionDangerId;

    private Integer prodId;

    private String content;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getAttentionDangerId() {
        return attentionDangerId;
    }

    public void setAttentionDangerId(Integer attentionDangerId) {
        this.attentionDangerId = attentionDangerId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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