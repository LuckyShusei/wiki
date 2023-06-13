package com.luckusyusei.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckusyusei.wiki.Response.EbookResp;
import com.luckusyusei.wiki.domain.Ebook;
import com.luckusyusei.wiki.domain.EbookExample;
import com.luckusyusei.wiki.mapper.EbookMapper;
import com.luckusyusei.wiki.req.EbookReq;
import com.luckusyusei.wiki.util.CopyUtil;
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
    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookexample = new EbookExample();
        EbookExample.Criteria criteria = ebookexample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }

        PageHelper.startPage(1,3);
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

        List<EbookResp> list = CopyUtil.copyList(ebooklist, EbookResp.class);

        return list;
    }
}
