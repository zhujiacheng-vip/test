package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Mr zhu
 * @Date 2020/9/7 23:28
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {



}
