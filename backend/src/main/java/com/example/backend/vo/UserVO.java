package com.example.backend.vo;

import com.example.backend.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVO implements Serializable {
    private List<User> users;
    private int total;
    private static final long serialVersionUID = 1L;
}