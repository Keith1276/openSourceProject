package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Repository;
import com.example.backend.entity.User;
import com.example.backend.result.PageResult;

import java.util.List;

/**
 * @author zjh
 * @description
 * @createDate 2024-12-5
 */
public interface BackendService extends IService<User> {
    /**
     * 搜索仓库
     *
     * @param keywords  关键词
     * @param language  语言
     * @param pageNum   页号
     * @param pageSize  页面大小
     */
    List<Repository> searchRepos(List<String> keywords, String language, Long pageNum, Long pageSize);

    /**
     * 搜索用户
     *
     * @param keywords  关键词
     * @param pageNum   页号
     * @param pageSize  页面大小
     */
    List<User> searchUsers(List<String> keywords, Long pageNum, Long pageSize);
}
