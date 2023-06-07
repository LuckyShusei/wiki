package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.domain.Test;
import com.luckusyusei.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController//返回字符串 @Controller 返回hello页面（前后端分离用不到）
public class TestController {

    @Value("${test.hello:Test}")
    private String testHello;

    @Resource
    private TestService testService;

    @GetMapping("/hello")//新增一个接口
    public String hello(){
        return "hello,world" + testHello;
    }
    @PostMapping("/hello/post")//新增一个接口
    public String helloPost(String name){
        return "hello,world,Post," + name;
    }
    @GetMapping("test/list")//新增一个接口
    public List<Test> list(){
        return testService.list();
    }

}
