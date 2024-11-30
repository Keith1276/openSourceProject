package com.example.backend.controller;

import com.example.backend.service.BackendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后端api接口")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class BackendController {
    @Resource
    private BackendService backendService;

    @GetMapping("/test")
    @Operation(summary = "测试运行")
    public String test() throws InterruptedException {
        return "success";
    }
//    @ApiOperation("添加完整信息")
//    @PostMapping("/insert")
//    public String insert(@RequestBody Data data) {
//    }

//    @ApiOperation("查询id对应的信息")
//    @GetMapping("/findById/{id}")
//    public Data findById(@PathVariable int id) {
//        return dataService.findById(id);
//    }
}