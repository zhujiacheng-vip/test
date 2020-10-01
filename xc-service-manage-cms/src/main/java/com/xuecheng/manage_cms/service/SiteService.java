package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Mr zhu
 * @Date 2020/9/7 23:32
 */
@Service
public class SiteService {

    @Resource
    private CmsSiteRepository cmsSiteRepository;


    /*
    *站点查询方法
    *  */
    public QueryResponseResult findSiteList(){

        List<CmsSite> all = cmsSiteRepository.findAll();

        QueryResult queryResult = new QueryResult();

        queryResult.setList(all);//数据列表

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);

        return queryResponseResult;


    }



}
