package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "express_company")
public class Company implements Serializable {
    @Id
    private Integer id;

    @Column(name = "express_name")
    private String expressName;

    private String status;

    @Column(name = "creata_time")
    private Date creataTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return express_name
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * @param expressName
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName == null ? null : expressName.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return creata_time
     */
    public Date getCreataTime() {
        return creataTime;
    }

    /**
     * @param creataTime
     */
    public void setCreataTime(Date creataTime) {
        this.creataTime = creataTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expressName=").append(expressName);
        sb.append(", status=").append(status);
        sb.append(", creataTime=").append(creataTime);
        sb.append("]");
        return sb.toString();
    }
}