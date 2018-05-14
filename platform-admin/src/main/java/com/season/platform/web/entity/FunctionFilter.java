package com.season.platform.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yingchun on 2017/9/1.
 */
@Data
@Entity
@Table(name = "tbl_function_filter")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class FunctionFilter extends BaseEntity {
    private static final long serialVersionUID = -4489812553857126394L;

    @Column(name = "[key]")
    private String key;

    @Column(name = "operator")
    private String operator;

    @Column(name = "[value]")
    private String value;

    @Column(name = "class_type")
    private String classType;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "roleId")
    private String roleId;

    @Column(name = "functionId")
    private String functionId;


}
