package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.DocQueryResp;
import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.req.DocQueryReq;
import com.luckusyusei.wiki.req.DocSaveReq;
import com.luckusyusei.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/all")//新增一个接口
    public CommonResp all(){
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list =docService.all();
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/list")//新增一个接口
    public CommonResp list(@Valid DocQueryReq req){
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
        PageResp<DocQueryResp> list =docService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")//新增一个save接口
    public CommonResp save(@Valid @RequestBody DocSaveReq req){
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }
    @DeleteMapping  ("/delete/{id}")//新增一个delete接口
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        docService.delete(id);
        return resp;
    }



}