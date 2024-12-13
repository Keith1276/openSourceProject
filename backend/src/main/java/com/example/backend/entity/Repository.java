package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 * @TableName repository
 */
@TableName(value ="repository")
@Data
public class Repository implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库id
     */
    private Integer repoId;

    /**
     * 仓库名
     */
    private String name;

    /**
     * 拥有者id
     */
    private Integer ownerId;

    /**
     * 拥有者账号
     */
    private String ownerLogin;

    /**
     * 网页url
     */
    private String htmlUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否为fork仓库
     */
    private Boolean fork;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 推送时间
     */
    private String pushedAt;

    /**
     * star数
     */
    private Integer stargazersCount;

    /**
     * watcher数
     */
    private Integer watchersCount;

    /**
     * 语言
     */
    private String language;

    /**
     * 是否有issues
     */
    private Boolean hasIssues;

    /**
     * 是否有discussions
     */
    private Boolean hasDiscussions;

    /**
     * fork数
     */
    private Integer forksCount;

    /**
     * open issue数
     */
    private Integer openIssuesCount;

    /**
     * 许可证
     */
    private String license;

    /**
     * 是否允许fork
     */
    private Boolean allowForking;

    /**
     * 分数
     */
    private Double score;

    /**
     * 匹配度得分
     */
    @TableField(exist = false)
    private Double matchScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}