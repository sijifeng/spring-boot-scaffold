package com.season.platform.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by yingchun on 2017/9/1.
 */
@Data
@Entity
@Table(name = "tbl_function")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Function extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 7823895619744279485L;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "url", length = 200)
    private String url;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "levelCode", length = 36)
    private String levelCode;

    @Column(name = "icon")
    private String icon;

    // 0=目录 1=功能 2=按钮
    @Column(name = "functype")
    private String functype;

    @Column(name = "remark", length = 1000)
    private String remark;

    @Transient
    private String parentName;

    @Transient
    private List<FunctionFilter> fflist;

    @Transient
    private String roleId;



}
