package com.season.platform.web.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by yingchun on 2017/9/1.
 */
@Data
public class TreeNode {
    private String text;

    private List<String> tags;

    private String id;

    private String parentId;

    private String levelCode;

    private List<TreeNode> nodes;

    private String icon;
}
