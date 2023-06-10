package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.domain.Demo;
import com.luckusyusei.wiki.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @GetMapping("/list")//新增一个接口
    public List<Demo> list(){
        return demoService.list();
    }

}
