package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.manage_cms.service.ConfigService;
import com.xuecheng.manage_cms.service.PageService;
import com.xuecheng.manage_cms.service.SiteService;
import com.xuecheng.manage_cms.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 17:24
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private PageService pageService;

    @Resource
    private SiteService siteService;

    @Resource
    private TemplateService templateService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        //System.out.println("前端传过来的对象"+queryPageRequest);

/*        //暂时用静态数据
        //定义queryResult
        QueryResult<CmsPage> queryResult =new QueryResult<>();
        List<CmsPage> list = new ArrayList<>();
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);
        queryResult.setTotal(1);

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;*/
        //调用service
        return pageService.findList(page, size, queryPageRequest);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {

        //System.out.println("我是前端传来的添加对象"+cmsPage);

        return pageService.add(cmsPage);

    }

    @Override
    @GetMapping("/getPageById/{id}")
    public CmsPage findById(@PathVariable("id") String id) {

        System.out.println("我是前端传过来的id"+id);

        return pageService.findPageById(id);
    }

    @Override
    @PostMapping("/updatePage/{id}")
    public CmsPageResult updatePage(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.updatePage(id,cmsPage);
    }

    @Override
    @GetMapping("/deletePage/{id}")
    public QueryResponseResult deletePage(@PathVariable("id") String id) {
        return pageService.deletePage(id);
    }

    @Override
    @GetMapping("/get")
    public void get() {
        System.out.println(0/1);

        ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
    }

}
