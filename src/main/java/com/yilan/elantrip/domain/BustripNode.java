package com.yilan.elantrip.domain;

import java.util.Date;

public class BustripNode {
    private Integer bustripNodeId;

    private Integer bustripId;

    private String nodeCode;

    private String nodeName;

    private Integer nodeType;

    private Integer activated;

    private Integer deleted;

    private Date updatedDate;

    private Date createdDate;

    public Integer getBustripNodeId() {
        return bustripNodeId;
    }

    public void setBustripNodeId(Integer bustripNodeId) {
        this.bustripNodeId = bustripNodeId;
    }

    public Integer getBustripId() {
        return bustripId;
    }

    public void setBustripId(Integer bustripId) {
        this.bustripId = bustripId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode == null ? null : nodeCode.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
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