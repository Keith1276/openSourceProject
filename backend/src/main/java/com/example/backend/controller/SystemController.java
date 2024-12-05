package com.example.backend.controller;

import com.example.backend.service.BackendService;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

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

//    /**
//     * 查看用户详细信息
//     *
//     * @param userId
//     * @return
//     */
//    @GetMapping("/check_user")
//    @Operation(summary = "查看用户详细信息")
//    public BaseResponse<UserVO> checkUser(@RequestParam Long userId) {
//        try {
//            User result = systemService.checkUser(userId);
//            UserVO userVO = UserVO.builder()
//                    .id(result.getId())
//                    .account(result.getAccount())
//                    .avatar(result.getAvatar())
//                    .profile(result.getProfile())
//                    .role(result.getRole())
//                    .email(result.getEmail())
//                    .build();
//            return ResultUtils.success(userVO);
//        } catch (Exception e) {
//            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
//        }
//    }
}
