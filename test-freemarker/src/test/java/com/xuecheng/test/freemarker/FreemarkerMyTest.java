package com.xuecheng.test.freemarker;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 8:03
 */

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerMyTest {

    @Test
    public void getFreeMarkerHtml() throws Exception {

        //定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        //定义模板
        //得到根路径
        String path = this.getClass().getResource("/").getPath();

        //定义模板的路径
        configuration.setDirectoryForTemplateLoading(new File(path+"/templates/"));

        //获取模板内容
        Template template = configuration.getTemplate("test.ftl","utf-8");

        //定义数据模型
        Map map = new HashMap();

        map.put("name","朱嘉诚");

        //静态化,传入模板和数据
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        InputStream inputStream = IOUtils.toInputStream(s);

        OutputStream outputStream = new FileOutputStream(new File("d:/test.html"));

        //把输入流里的数据复制到输出流里
        IOUtils.copy(inputStream, outputStream);

        outputStream.close();

        inputStream.close();

    }

}
