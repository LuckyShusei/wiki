package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.Response.EbookResp;
import com.luckusyusei.wiki.req.EbookReq;
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
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list =ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

}