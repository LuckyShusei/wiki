package com.luckusyusei.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//返回字符串 @Controller 返回hello页面（前后端分离用不到）
public class TestController {
    @GetMapping("/hello")//新增一个接口
    public String hello(){
        return "hello world";
    }
    @PostMapping("/hello/post")//新增一个接口
    public String helloPost(String name){
        return "hello world!Post," + name;
    }
}
