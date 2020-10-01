package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 8:30
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

}
