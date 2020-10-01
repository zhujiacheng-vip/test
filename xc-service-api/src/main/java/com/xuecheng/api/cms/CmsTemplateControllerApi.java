package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 9:00
 */
@Api(value="cms模板管理接口",description = "cms模板管理接口，提供页面的增、删、改、查")
public interface CmsTemplateControllerApi {

    //模板列表查询
    @ApiOperation("模板列表查询")
    public QueryResponseResult findTemplateList();

}
