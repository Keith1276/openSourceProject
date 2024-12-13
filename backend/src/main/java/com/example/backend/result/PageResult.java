package com.example.backend.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    /**
     *
     */
    private long pageNum;
    /**
     *
     */
    private long pageSize;
    /**
     *
     */
    private long total;

    private List<T> list;

    private static final long serialVersionUID = 1L;
}
