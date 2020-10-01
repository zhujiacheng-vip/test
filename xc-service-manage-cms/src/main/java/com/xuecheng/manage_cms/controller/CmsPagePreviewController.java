package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * @Author Mr zhu
 * @Date 2020/9/10 9:39
 */

/*beseContrller提供了请求和重定向*/
@Controller
public class CmsPagePreviewController extends BaseController {

    @Resource
    private PageService pageService;

    //页面预览
    @GetMapping("/cms/preview/{pageId}")
    public void pagePreview(@PathVariable String pageId) throws IOException {
        String pageHtmlByPageId = pageService.getPageHtmlById(pageId);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(pageHtmlByPageId.getBytes("UTF-8"));
    }

}
