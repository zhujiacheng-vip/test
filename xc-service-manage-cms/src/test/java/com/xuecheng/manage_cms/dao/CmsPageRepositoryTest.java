package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Resource
    private CmsSiteRepository cmsSiteRepository;

    @Resource
    private CmsTemplateRepository cmsTemplateRepository;

    @Resource
    private PageService pageService;

    @Resource
    private CmsConfigRepository cmsConfigRepository;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testFindAll() {
        List<CmsPage> all = cmsPageRepository.findAll();

        all.forEach(intdex -> {

            System.out.println(intdex);

        });

    }

    //分页查询
    @Test
    public void testFindPage() {
        //分页参数
        int page = 1;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    //修改
    @Test
    public void testUpdate() {
        //查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5b4b1d8bf73c6623b03f8cec");
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            //设置要修改值
            cmsPage.setPageAliase("test01");
            //...
            //修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }

    }

    //根据页面名称查询
    @Test
    public void testfindByPageName() {
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面");
        System.out.println(cmsPage);
    }

    //自定义条件查询测试
    @Test
    public void testFindAllByExample() {

       /* int page = 1;

        int size = 10;

        Pageable pageable = PageRequest.of(page, size);

        //条件查询对象
        CmsPage cmsPage = new CmsPage();

        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");

        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();

        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);

        List<CmsPage> content = all.getContent();

        System.out.println(content);*/

        //定义Example


        //条件构造器
        //ExampleMatcher exampleMatcher = ExampleMatcher.matching();

       // exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        //页面别名模糊查询，需要自定义字符串的匹配器实现模糊查询
        //ExampleMatcher.GenericPropertyMatchers.contains() 包含
        //ExampleMatcher.GenericPropertyMatchers.startsWith()//开头匹配
        //条件值
        //CmsPage cmsPage = new CmsPage();

        //站点ID
        //cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");

        //模板ID
        //cmsPage.setTemplateId("5a962c16b00ffc514038fafd");

        //cmsPage.setPageAliase("课程");

        //创建条件实例
       // Example<CmsPage> example = Example.of(cmsPage, );

        Pageable pageable = new PageRequest(0, 6);

        Page<CmsPage> all = cmsPageRepository.findAll(pageable);

        all.getContent().forEach(page->{

            System.out.println(page);

        });

    }

    @Test
    public void findSitelList() {

        QueryPageRequest queryPageRequest = new QueryPageRequest();

        queryPageRequest.setPageAliase("课程");

        QueryResponseResult list = pageService.findList(1, 6, queryPageRequest);

        System.out.println(list.getQueryResult().getList());

    }

    @Test
    public void findTemplateList() {

        List<CmsTemplate> all = cmsTemplateRepository.findAll();

        System.out.println(all);

    }

    @Test
    public void findPageById() {

        Optional<CmsPage> byId = cmsPageRepository.findById("5f56e1d64681990ba401b22d");

        if (byId.isPresent()) {

            System.out.println(byId.get());

        }

    }

    @Test
    public void deletePage() {

        cmsPageRepository.deleteById("111");

    }

    @Test
    public void getConfigById() {

        Optional<CmsConfig> byId = cmsConfigRepository.findById("5a791725dd573c3574ee333f");

        if (byId.isPresent()) {

            System.out.println(byId.get());

        }

    }

    @Test
    public void testRestTemplate() {

        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getConfigById/5a791725dd573c3574ee333f", Map.class);

        System.out.println(forEntity);

    }

    @Test
    public void getHtml(){

        String pageHtmlById = pageService.getPageHtmlById("5f56d1374681992da885a657");

        System.out.println(pageHtmlById);

    }

}
