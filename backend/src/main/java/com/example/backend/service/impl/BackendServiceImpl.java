package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.backend.entity.Repository;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.RepositoryMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.BackendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zjh
 * @description
 * @createDate 2024-12-5
 */
@Service
public class BackendServiceImpl extends ServiceImpl<UserMapper, User>
        implements BackendService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public List<Repository> searchRepos(List<String> keywords, String language, Long pageNum, Long pageSize) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new BaseException("pageSize,pageNum 必须为正整数");
        }
        // 关键词匹配
        QueryWrapper<Repository> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "repo_id", "name", "owner_id", "owner_login", "html_url", "description",
                                    "stargazers_count", "language", "forks_count", "open_issues_count", "score");
        if (!language.isEmpty()) {
            queryWrapper.eq("language", language);
        }
        if (!keywords.isEmpty()) {
            for (int i = 0; i < keywords.size(); i++) {
                String keyword = keywords.get(i);
                String lowerKeyword = keyword.toLowerCase();
                if (i == 0) {
                    queryWrapper.like("LOWER(name)", lowerKeyword)
                            .or()
                            .like("LOWER(description)", lowerKeyword);
                } else {
                    queryWrapper.or(qw -> qw.like("LOWER(name)", lowerKeyword)
                            .or()
                            .like("LOWER(description)", lowerKeyword));
                }
            }
        } else {
            queryWrapper.orderByDesc("score");
            Page<Repository> page = new Page<>(pageNum, pageSize);
            Page<Repository> repositoryPage = repositoryMapper.selectPage(page, queryWrapper);
            return repositoryPage.getRecords();
        }
        List<Repository> repositories = repositoryMapper.selectList(queryWrapper);
        // 匹配度得分
        double[] keywordWeights = {1.0, 0.8, 0.6, 0.4, 0.2};
        for (Repository repo : repositories) {
            double matchScore = 0.0;
            for (int i = 0; i < keywords.size(); i++) {
                String keyword = keywords.get(i);
                double keywordWeight = keywordWeights[Math.min(i, keywordWeights.length - 1)];
                boolean nameMatch = false;
                if (repo.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    nameMatch = true;
                }
                int descriptionMatchCount = 0;
                if (repo.getDescription() != null && repo.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    int index = 0;
                    while ((index = repo.getDescription().indexOf(keyword, index)) != -1) {
                        descriptionMatchCount++;
                        index++;
                    }
                }
                double tmp = 0;
                if (nameMatch) {
                    if (descriptionMatchCount > 0) {
                        tmp = Math.max(8, 5 + descriptionMatchCount);
                        tmp = Math.min(tmp, 10);
                    } else {
                        tmp = 5;
                    }
                } else {
                    tmp = (double) descriptionMatchCount;
                    tmp = Math.min(tmp, 5);
                }
                matchScore += keywordWeight * tmp;
            }
            repo.setMatchScore(matchScore);
        }
        // 归一化
        double minMatchScore = Double.MAX_VALUE;
        double maxMatchScore = Double.MIN_VALUE;
        for (Repository repo : repositories) {
            double matchScore = repo.getMatchScore();
            if (matchScore < minMatchScore) {
                minMatchScore = matchScore;
            }
            if (matchScore > maxMatchScore) {
                maxMatchScore = matchScore;
            }
        }
        for (Repository repo : repositories) {
            double matchScore = repo.getMatchScore();
            double normalizedMatchScore = 0;
            if (maxMatchScore != minMatchScore) {
                normalizedMatchScore = (matchScore - minMatchScore) / (maxMatchScore - minMatchScore);
            } else {
                normalizedMatchScore = 0;
            }
            repo.setMatchScore(normalizedMatchScore);
        }
        // 按搜索得分排序
        repositories.sort((r1, r2) -> Double.compare(70 * r2.getMatchScore() + 0.3 * r2.getScore(), 70 * r1.getMatchScore() + 0.3 * r1.getScore()));
        // 分页
        int startIndex = (int) ((pageNum - 1) * pageSize);
        int endIndex = (int) Math.min(startIndex + pageSize, repositories.size());
        return repositories.subList(startIndex, endIndex);
    }

    @Override
    public List<User> searchUsers(List<String> keywords, Long pageNum, Long pageSize) {
        if (pageSize <= 0 || pageNum <= 0) {
            throw new BaseException("pageSize,pageNum 必须为正整数");
        }
        // 关键词匹配
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!keywords.isEmpty()) {
            for (int i = 0; i < keywords.size(); i++) {
                String keyword = keywords.get(i);
                String lowerKeyword = keyword.toLowerCase();
                if (i == 0) {
                    queryWrapper.like("LOWER(login)", lowerKeyword)
                            .or()
                            .like("LOWER(email)", lowerKeyword);
                } else {
                    queryWrapper.or(qw -> qw.like("LOWER(login)", lowerKeyword)
                            .or()
                            .like("LOWER(email)", lowerKeyword));
                }
            }
        } else {
            queryWrapper.orderByDesc("score");
            Page<User> page = new Page<>(pageNum, pageSize);
            Page<User> userPage = userMapper.selectPage(page, queryWrapper);
            return userPage.getRecords();
        }
        List<User> users = userMapper.selectList(queryWrapper);
        // 匹配度得分
        double[] keywordWeights = {1.0, 0.8, 0.6, 0.4, 0.2};
        for (User user: users) {
            double matchScore = 0.0;
            for (int i = 0; i < keywords.size(); i++) {
                String keyword = keywords.get(i);
                double keywordWeight = keywordWeights[Math.min(i, keywordWeights.length - 1)];
                boolean loginMatch = false;
                if (user.getLogin().toLowerCase().contains(keyword.toLowerCase())) {
                    loginMatch = true;
                }
                boolean emailMatch = false;
                if (user.getEmail() != null && user.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
                    emailMatch = true;
                }
                double tmp = 0;
                if (loginMatch && emailMatch) {
                    tmp = 3;
                } else if (loginMatch) {
                    tmp = 2;
                } else if (emailMatch) {
                    tmp = 1;
                }
                matchScore += keywordWeight * tmp;
            }
            user.setMatchScore(matchScore);
        }
        // 归一化
        double minMatchScore = Double.MAX_VALUE;
        double maxMatchScore = Double.MIN_VALUE;
        for (User user : users) {
            double matchScore = user.getMatchScore();
            if (matchScore < minMatchScore) {
                minMatchScore = matchScore;
            }
            if (matchScore > maxMatchScore) {
                maxMatchScore = matchScore;
            }
        }
        for (User user : users) {
            double matchScore = user.getMatchScore();
            double normalizedMatchScore = 0;
            if (maxMatchScore != minMatchScore) {
                normalizedMatchScore = (matchScore - minMatchScore) / (maxMatchScore - minMatchScore);
            } else {
                normalizedMatchScore = 0;
            }
            user.setMatchScore(normalizedMatchScore);
        }
        // 按搜索得分排序
        users.sort((r1, r2) -> Double.compare(70 * r2.getMatchScore() + 0.3 * r2.getScore(), 70 * r1.getMatchScore() + 0.3 * r1.getScore()));
        // 分页
        int startIndex = (int) ((pageNum - 1) * pageSize);
        int endIndex = (int) Math.min(startIndex + pageSize, users.size());
        return users.subList(startIndex, endIndex);
    }
}




