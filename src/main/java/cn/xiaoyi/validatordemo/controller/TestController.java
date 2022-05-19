package cn.xiaoyi.validatordemo.controller;

import cn.xiaoyi.validatordemo.entity.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("test")
@Validated
public class TestController {



    /**
     * 正则表达式
     * @param year
     * @return
     */
    @PostMapping("testRegexp")
    public Result validatedTest(@Pattern (regexp = "^[\\d]{4}$",message = "年份日期不合法！")@RequestParam("year") String year){
        return Result.success("参数合法",year);

    }


}
