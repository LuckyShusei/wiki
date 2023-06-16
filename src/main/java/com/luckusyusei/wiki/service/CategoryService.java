package com.luckusyusei.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckusyusei.wiki.Response.CategoryQueryResp;
import com.luckusyusei.wiki.Response.PageResp;
import com.luckusyusei.wiki.domain.Category;
import com.luckusyusei.wiki.domain.CategoryExample;
import com.luckusyusei.wiki.mapper.CategoryMapper;
import com.luckusyusei.wiki.req.CategoryQueryReq;
import com.luckusyusei.wiki.req.CategorySaveReq;
import com.luckusyusei.wiki.util.CopyUtil;
import com.luckusyusei.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        CategoryExample categoryexample = new CategoryExample();
        categoryexample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryexample.createCriteria();

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categorylist = categoryMapper.selectByExample(categoryexample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorylist);
        LOG.info("总行数：{}" , pageInfo.getTotal());
        LOG.info("总页数：{}" ,pageInfo.getPages());

//        List<CategoryResp> respList =new ArrayList<>();
//        for (Category category : categorylist) {
////            CategoryResp categoryResp = new CategoryResp();
////            BeanUtils.copyProperties(category,categoryResp);
//
//            CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
//            respList.add(categoryResp);
//        }

        List<CategoryQueryResp> list = CopyUtil.copyList(categorylist, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    public List<CategoryQueryResp> all() {
        CategoryExample categoryexample = new CategoryExample();
        categoryexample.setOrderByClause("sort asc");
        List<Category> categorylist = categoryMapper.selectByExample(categoryexample);
        //列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categorylist, CategoryQueryResp.class);

        return list;
    }
    public void save(CategorySaveReq req){
        Category category = CopyUtil.copy(req,Category.class);
        if(ObjectUtils.isEmpty(req.getId())){
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else{//更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }
    public void delete(Long id){
      categoryMapper.deleteByPrimaryKey(id);
    }
}
