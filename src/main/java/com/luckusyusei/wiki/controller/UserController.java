package com.luckusyusei.wiki.controller;

import com.luckusyusei.wiki.Response.CommonResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.Response.UserQueryResp;
import com.luckusyusei.wiki.req.UserQueryReq;
import com.luckusyusei.wiki.req.UserResetPasswordReq;
import com.luckusyusei.wiki.req.UserSaveReq;
import com.luckusyusei.wiki.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")//新增一个接口
    public CommonResp list(@Valid UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list =userService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")//新增一个save接口
    public CommonResp save(@Valid @RequestBody UserSaveReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }
    @DeleteMapping  ("/delete/{id}")//新增一个delete接口
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }
    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;

    }

}
