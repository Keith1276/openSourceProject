package com.example.backend.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索项目请求
 */
@Data
public class SearchUserRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private List<String> keywords;

    private Long pageNum;

    private Long pageSize;
}
