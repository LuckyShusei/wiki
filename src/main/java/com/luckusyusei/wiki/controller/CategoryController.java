package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.CategoryQueryResp;
import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.req.CategoryQueryReq;
import com.luckusyusei.wiki.req.CategorySaveReq;
import com.luckusyusei.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")//新增一个接口
    public CommonResp all(){
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list =categoryService.all();
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/list")//新增一个接口
    public CommonResp list(@Valid CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list =categoryService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")//新增一个save接口
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }
    @DeleteMapping  ("/delete/{id}")//新增一个delete接口
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }



}
