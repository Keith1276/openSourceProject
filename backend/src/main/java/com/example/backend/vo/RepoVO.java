package com.example.backend.vo;

import com.example.backend.entity.Repository;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RepoVO implements Serializable {
    private List<Repository> repositories;
    private int total;
    private static final long serialVersionUID = 1L;
}