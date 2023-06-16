package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.Response.EbookResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.req.EbookQueryReq;
import com.luckusyusei.wiki.req.EbookSaveReq;
import com.luckusyusei.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")//新增一个接口
    public CommonResp list(EbookQueryReq req){
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list =ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")//新增一个save接口
    public CommonResp save(@RequestBody EbookSaveReq req){
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }


}
