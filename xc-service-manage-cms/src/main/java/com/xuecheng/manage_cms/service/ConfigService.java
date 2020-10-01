package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 8:35
 */
@Service
public class ConfigService {

    @Resource
    private CmsConfigRepository cmsConfigRepository;


    public CmsConfig findConfigById(String id){

        Optional<CmsConfig> byId = cmsConfigRepository.findById(id);

        if(byId.isPresent()){

            CmsConfig cmsConfig = byId.get();

            return cmsConfig;

        }

        return null;

    }

}
