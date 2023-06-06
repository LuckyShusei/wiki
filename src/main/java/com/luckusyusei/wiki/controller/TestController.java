package com.luckusyusei.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//返回字符串 @Controller 返回hello页面（前后端分离用不到）
public class TestController {
    @RequestMapping("/hello")//新增一个接口
    public String hello(){
        return "hello world";
    }
}
