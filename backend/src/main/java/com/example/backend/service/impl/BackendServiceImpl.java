package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.BackendService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


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

//    @Override
//    public User checkUser(Long userId) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", userId);
//        User user = userMapper.selectOne(queryWrapper);
//        if (user == null) {
//            throw new BaseException("用户不存在");
//        }
//        return user;
//    }
}




