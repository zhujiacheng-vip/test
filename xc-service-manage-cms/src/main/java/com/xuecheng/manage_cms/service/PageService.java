package com.xuecheng.manage_cms.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Resource
    private CmsPageRepository cmsPageRepository;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CmsTemplateRepository cmsTemplateRepository;

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFSBucket gridFSBucket;

    /**
     * 页面查询方法
     *
     * @param page             页码，从1开始记数
     * @param size             每页记录数
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {


        //查询条件判断
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }

        //条件值对象
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if (StringUtils.isNotBlank(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotBlank(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase",
                ExampleMatcher.GenericPropertyMatchers.contains());

        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);

        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);

      /*  //自定义条件查询
        //定义条件匹配器
        // 页面别名：模糊匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();

        exampleMatcher = exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage = new CmsPage();

        //站点Id：精确匹配
        if(!StringUtils.isEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        // 模板Id：精确匹配
        if(!StringUtils.isEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //创建条件实例
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //分页参数
        if(page <=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;*/
    }

    public CmsPageResult add(CmsPage cmsPage) {

        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());

        if (cmsPage1 == null) {

            cmsPage.setPageId(null);

            cmsPageRepository.save(cmsPage);

            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);

        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    public CmsPage findPageById(String id) {

        CmsPage cmsPage = null;

        Optional<CmsPage> byId = cmsPageRepository.findById(id);

        if (byId.isPresent()) {

            cmsPage = byId.get();

        }

        return cmsPage;

    }

    public CmsPageResult updatePage(String id, CmsPage cmsPage) {
        //根据id从数据库查询页面信息
        CmsPage one = this.findPageById(id);
        if (one != null) {
            //准备更新数据
            //设置要修改的数据
            //更新模板id
            one.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            one.setPageName(cmsPage.getPageName());
            //更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            one.setDataUrl(cmsPage.getDataUrl());
            //提交修改
            cmsPageRepository.save(one);
            return new CmsPageResult(CommonCode.SUCCESS, one);
        }
        //修改失败
        return new CmsPageResult(CommonCode.FAIL, null);
    }


    public QueryResponseResult deletePage(String id) {

        try {
            cmsPageRepository.deleteById(id);

            return new QueryResponseResult(CommonCode.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new QueryResponseResult(CommonCode.FAIL, null);
        }

    }


    //生成静态化页面
    public String getPageHtmlById(String pageId) {

        //根据数据模型
        Map map = getModelByPageId(pageId);

        if (map == null) {

            ExceptionCast.cast(CmsCode.CMS_COURSE_PERVIEWISNULL);

        }

        //获取模板
        String templateInfo = getTemplateInfo(pageId);

        if (StringUtils.isEmpty(templateInfo)) {

            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);

        }
        //进行静态化生成
        return integration(map,templateInfo);

    }


    //获取数据模型
    private Map getModelByPageId(String pageId) {

        //单查获取到page对象
        CmsPage cmsPage = this.findPageById(pageId);

        //页面不存在
        if (cmsPage == null) {

            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);

        }

        //获取pege对象里面的url
        String dataUrl = cmsPage.getDataUrl();

        if (StringUtils.isEmpty(dataUrl)) {

            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);

        }

        ResponseEntity<Map> entity = restTemplate.getForEntity(dataUrl, Map.class);

        Map map = entity.getBody();

        return map;

    }

    //获取页面模板
    private String getTemplateInfo(String pageId) {

        CmsPage cmsPage = findPageById(pageId);

        if (cmsPage == null) {

            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);

        }

        Optional<CmsTemplate> byId = cmsTemplateRepository.findById(cmsPage.getTemplateId());

        if (byId.isPresent()) {

            CmsTemplate cmsTemplate = byId.get();

            //根据模板ID获取文件
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(cmsTemplate.getTemplateFileId())));

            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());

            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);

            try {
                return IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }


    //进行数据填充
    private String integration(Map map, String templateInfo) {

        Configuration configuration = new Configuration(Configuration.getVersion());

        //文本模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();

        //设置名称和内容
        stringTemplateLoader.putTemplate("template", templateInfo);

        //将文本加载器添加到配置里
        configuration.setTemplateLoader(stringTemplateLoader);

        try {
            Template template = configuration.getTemplate("template", "utf-8");
            // 生成
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
