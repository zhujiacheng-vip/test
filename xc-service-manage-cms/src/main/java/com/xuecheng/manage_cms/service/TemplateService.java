package com.xuecheng.manage_cms.service;


import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Mr zhu
 * @Date 2020/9/8 8:52
 */
@Service
public class TemplateService {

    @Resource
    private CmsTemplateRepository cmsTemplateRepository;

    public QueryResponseResult findTemplateList(){

        List<CmsTemplate> templateList = cmsTemplateRepository.findAll();

        QueryResult queryResult = new QueryResult();

        queryResult.setList(templateList);

        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);

    }


}
