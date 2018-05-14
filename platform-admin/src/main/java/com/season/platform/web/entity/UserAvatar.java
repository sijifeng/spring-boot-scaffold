package com.season.platform.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yingchun on 2017/8/31.
 */
@Entity
@Table(name = "tbl_user_avatar")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UserAvatar extends BaseEntity {

    @Column(name="user_id",length=32)
    private String userId;

    @Column(name="name")
    private String name;

    @Column(name="src")
    private String src;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}