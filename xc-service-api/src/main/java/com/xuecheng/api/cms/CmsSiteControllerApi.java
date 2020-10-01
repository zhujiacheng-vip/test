package com.xuecheng.api.cms;

import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 8:58
 */
@Api(value="cms站点管理接口",description = "cms站点管理接口，提供页面的增、删、改、查")
public interface CmsSiteControllerApi {

    //站点列表查询
    @ApiOperation("站点列表查询")
    public QueryResponseResult findSitelList();

}
