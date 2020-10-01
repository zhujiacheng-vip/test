package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author Mr zhu
 * @Date 2020/9/8 8:47
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {


}
