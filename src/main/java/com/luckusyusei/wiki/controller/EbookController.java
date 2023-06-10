package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.domain.Ebook;
import com.luckusyusei.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")//新增一个接口
    public CommonResp list(){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list =ebookService.list();
        resp.setContent(list);
        return resp;
    }

}
