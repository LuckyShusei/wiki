package com.luckusyusei.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckusyusei.wiki.Response.DocQueryResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.domain.Content;
import com.luckusyusei.wiki.domain.Doc;
import com.luckusyusei.wiki.domain.DocExample;
import com.luckusyusei.wiki.mapper.ContentMapper;
import com.luckusyusei.wiki.mapper.DocMapper;
import com.luckusyusei.wiki.req.DocQueryReq;
import com.luckusyusei.wiki.req.DocSaveReq;
import com.luckusyusei.wiki.util.CopyUtil;
import com.luckusyusei.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DocService {
    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);
    @Resource
    private DocMapper docMapper;

    @Resource
    private ContentMapper contentMapper;
    @Resource
    private SnowFlake snowFlake;

    public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docexample = new DocExample();
        docexample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docexample.createCriteria();

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> doclist = docMapper.selectByExample(docexample);

        PageInfo<Doc> pageInfo = new PageInfo<>(doclist);
        LOG.info("总行数：{}" , pageInfo.getTotal());
        LOG.info("总页数：{}" ,pageInfo.getPages());

//        List<DocResp> respList =new ArrayList<>();
//        for (Doc doc : doclist) {
////            DocResp docResp = new DocResp();
////            BeanUtils.copyProperties(doc,docResp);
//
//            DocResp docResp = CopyUtil.copy(doc, DocResp.class);
//            respList.add(docResp);
//        }

        List<DocQueryResp> list = CopyUtil.copyList(doclist, DocQueryResp.class);

        PageResp<DocQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    public List<DocQueryResp> all(Long ebookId) {
        DocExample docexample = new DocExample();
        docexample.createCriteria().andEbookIdEqualTo(ebookId);
        docexample.setOrderByClause("sort asc");
        List<Doc> doclist = docMapper.selectByExample(docexample);
        //列表复制
        List<DocQueryResp> list = CopyUtil.copyList(doclist, DocQueryResp.class);

        return list;
    }

    /**
     * 保存
     */
    public void save(DocSaveReq req){
        Doc doc = CopyUtil.copy(req,Doc.class);
        Content content = CopyUtil.copy(req,Content.class);
        if(ObjectUtils.isEmpty(req.getId())){
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
            content.setId(doc.getId());
            contentMapper.insert(content);
        }else{//更新
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if(count == 0){
                contentMapper.insert(content);
            }
            }
    }
    public void delete(Long id){
      docMapper.deleteByPrimaryKey(id);
    }
    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
//        // 文档阅读数+1
//        docMapperCust.increaseViewCount(id);
//        if (ObjectUtils.isEmpty(content)) {
//            return "";
//        } else {
//            return content.getContent();
//        }
        return content.getContent();
    }
}
