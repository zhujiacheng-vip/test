package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 9:01
 */
@Api(value="cms轮播图管理接口",description = "cms轮播图管理接口，提供页面的增、删、改、查")
public interface CmsConfigControllerApi {

    //根据ID查询轮播图
    @ApiOperation("根据ID查询轮播图")
    public CmsConfig findConfigById(String id);

}
