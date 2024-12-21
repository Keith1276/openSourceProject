package com.example.backend.vo;

import com.example.backend.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageVO implements Serializable {
    private RepoVO repoVO;
    private User user;
    private static final long serialVersionUID = 1L;
}