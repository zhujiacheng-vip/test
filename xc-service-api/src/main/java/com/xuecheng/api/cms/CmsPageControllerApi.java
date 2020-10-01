package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);


    //新增页面
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);

    //通过ID查询页面
    @ApiOperation("通过ID查询页面")
    public CmsPage findById(String id);

    //修改页面
    @ApiOperation("通过ID修改页面")
    public CmsPageResult updatePage(String id, CmsPage cmsPage);

    //删除页面
    @ApiOperation("通过ID删除页面")
    public QueryResponseResult deletePage(String id);

    //测试异常
    @ApiOperation("测试全局捕获异常")
    public void get();

}
