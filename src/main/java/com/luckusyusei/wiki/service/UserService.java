package com.luckusyusei.wiki.service;

import com.github.pagehelper.PageInfo;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.Response.UserQueryResp;
import com.luckusyusei.wiki.domain.User;
import com.luckusyusei.wiki.domain.UserExample;
import com.luckusyusei.wiki.mapper.UserMapper;
import com.luckusyusei.wiki.req.UserQueryReq;
import com.luckusyusei.wiki.req.UserSaveReq;
import com.luckusyusei.wiki.util.CopyUtil;
import com.luckusyusei.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userexample = new UserExample();
        UserExample.Criteria criteria = userexample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andNameLike(req.getLoginName() );
        }

//        PageHelper.startPage(req.getPage(),req.getSize());
        List<User> userlist = userMapper.selectByExample(userexample);

        PageInfo<User> pageInfo = new PageInfo<>(userlist);
        LOG.info("总行数：{}" , pageInfo.getTotal());
        LOG.info("总页数：{}" ,pageInfo.getPages());

//        List<UserResp> respList =new ArrayList<>();
//        for (User user : userlist) {
////            UserResp userResp = new UserResp();
////            BeanUtils.copyProperties(user,userResp);
//
//            UserResp userResp = CopyUtil.copy(user, UserResp.class);
//            respList.add(userResp);
//        }

        List<UserQueryResp> list = CopyUtil.copyList(userlist, UserQueryResp.class);

        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    public void save(UserSaveReq req){
        User user = CopyUtil.copy(req,User.class);
        if(ObjectUtils.isEmpty(req.getId())){
            user.setId(snowFlake.nextId());
            userMapper.insert(user);
        }else{//更新
            userMapper.updateByPrimaryKey(user);
        }
    }
    public void delete(Long id){
      userMapper.deleteByPrimaryKey(id);
    }
}
