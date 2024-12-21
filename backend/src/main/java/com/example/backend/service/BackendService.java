package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Repository;
import com.example.backend.entity.User;
import com.example.backend.result.PageResult;
import com.example.backend.vo.RepoVO;
import com.example.backend.vo.UserPageVO;
import com.example.backend.vo.UserVO;

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
     * @param languages  语言
     * @param licenses   许可证
     * @param pageNum   页号
     * @param pageSize  页面大小
     */
    RepoVO searchRepos(List<String> keywords, List<String> languages, List<String> licenses, Long pageNum, Long pageSize);

    /**
     * 搜索用户
     *
     * @param keywords  关键词
     * @param pageNum   页号
     * @param pageSize  页面大小
     */
    UserVO searchUsers(List<String> keywords, Long pageNum, Long pageSize);

    /**
     * 开发者仓库
     *
     * @param userId    开发者
     * @param pageNum   页号
     * @param pageSize  页面大小
     */
    UserPageVO userPage(Integer userId, Long pageNum, Long pageSize);
}
