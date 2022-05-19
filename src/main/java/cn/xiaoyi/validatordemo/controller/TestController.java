package cn.xiaoyi.validatordemo.controller;

import cn.xiaoyi.validatordemo.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("validatedTest")
    public Result validatedTest(){
        return Result.success();
    }
}
