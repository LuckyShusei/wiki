package com.luckusyusei.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckusyusei.wiki.Response.EbookQueryResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.domain.Ebook;
import com.luckusyusei.wiki.domain.EbookExample;
import com.luckusyusei.wiki.mapper.EbookMapper;
import com.luckusyusei.wiki.req.EbookQueryReq;
import com.luckusyusei.wiki.req.EbookSaveReq;
import com.luckusyusei.wiki.util.CopyUtil;
import com.luckusyusei.wiki.util.SnowFlake;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;
    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req) {
        EbookExample ebookexample = new EbookExample();
        EbookExample.Criteria criteria = ebookexample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(req.getCategoryId2())) {
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookexample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklist);
        LOG.info("总行数：{}" , pageInfo.getTotal());
        LOG.info("总页数：{}" ,pageInfo.getPages());

//        List<EbookResp> respList =new ArrayList<>();
//        for (Ebook ebook : ebooklist) {
////            EbookResp ebookResp = new EbookResp();
////            BeanUtils.copyProperties(ebook,ebookResp);
//
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//            respList.add(ebookResp);
//        }

        List<EbookQueryResp> list = CopyUtil.copyList(ebooklist, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req,Ebook.class);
        if(ObjectUtils.isEmpty(req.getId())){
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else{//更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }
    public void delete(Long id){
      ebookMapper.deleteByPrimaryKey(id);
    }
}
