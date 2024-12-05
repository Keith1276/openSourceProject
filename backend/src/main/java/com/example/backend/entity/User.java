package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 网页url
     */
    private String htmlUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * follower数量
     */
    private Integer followers;

    /**
     * 公共仓库数量
     */
    private Integer publicRepos;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}