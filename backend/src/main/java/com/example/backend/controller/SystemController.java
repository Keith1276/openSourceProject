package com.example.backend.controller;

import com.example.backend.dto.SearchRepoRequest;
import com.example.backend.dto.SearchUserRequest;
import com.example.backend.entity.Repository;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.result.BaseResponse;
import com.example.backend.result.ErrorCode;
import com.example.backend.result.PageResult;
import com.example.backend.result.ResultUtils;
import com.example.backend.service.BackendService;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author
 * @Description：
 * @date
 */
@Tag(name = "后端相关接口")
@RestController
@RequestMapping("/api")
@Slf4j
public class SystemController {
    @Resource
    private BackendService systemService;

    @GetMapping("/test")
    @Operation(summary = "测试运行")
    public String test() {
        return "success";
    }

    /**
     * 搜索仓库
     *
     * @param searchRepoRequest
     * @return
     */
    @PostMapping("/search/repos")
    @Operation(summary = "搜索仓库")
    public BaseResponse<List<Repository>> searchRepos(@RequestBody SearchRepoRequest searchRepoRequest) {
        if (searchRepoRequest == null) {
            throw new BaseException("请求参数为空");
        }
        List<String> keywords = searchRepoRequest.getKeywords();
        List<String> languages = searchRepoRequest.getLanguages();
        List<String> licenses = searchRepoRequest.getLicenses();
        Long pageNum = searchRepoRequest.getPageNum();
        Long pageSize = searchRepoRequest.getPageSize();
        try {
            List<Repository> result = systemService.searchRepos(keywords, languages, licenses, pageNum, pageSize);
            return ResultUtils.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * 搜索开发者
     *
     * @param searchUserRequest
     * @return
     */
    @PostMapping("/search/users")
    @Operation(summary = "搜索开发者")
    public BaseResponse<List<User>> searchUsers(@RequestBody SearchUserRequest searchUserRequest) {
        if (searchUserRequest == null) {
            throw new BaseException("请求参数为空");
        }
        List<String> keywords = searchUserRequest.getKeywords();
        Long pageNum = searchUserRequest.getPageNum();
        Long pageSize = searchUserRequest.getPageSize();
        try {
            List<User> result = systemService.searchUsers(keywords, pageNum, pageSize);
            return ResultUtils.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }
}
