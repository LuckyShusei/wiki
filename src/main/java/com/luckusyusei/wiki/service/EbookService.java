package com.luckusyusei.wiki.service;

import com.luckusyusei.wiki.Response.EbookResp;
import com.luckusyusei.wiki.domain.Ebook;
import com.luckusyusei.wiki.domain.EbookExample;
import com.luckusyusei.wiki.mapper.EbookMapper;
import com.luckusyusei.wiki.req.EbookReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;
    public List<EbookResp> list(EbookReq req){
        EbookExample ebookexample = new EbookExample();
        EbookExample.Criteria criteria = ebookexample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookexample);

        List<EbookResp> respList =new ArrayList<>();
        for (Ebook ebook : ebooklist) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);
            respList.add(ebookResp);
        }
        return respList;
    }
}
