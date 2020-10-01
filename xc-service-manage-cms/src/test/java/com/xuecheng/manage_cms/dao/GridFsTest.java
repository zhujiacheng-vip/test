package com.xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 9:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFSBucket gridFSBucket;

    @Test
    public void setTemplateTest() throws IOException {

      /*  FileInputStream fileInputStream = new FileInputStream(new File("D:/test.html"));

        ObjectId store = gridFsTemplate.store(fileInputStream, "test.ftl");

        System.out.println(store);*/

        //GridFsResource resource = gridFsTemplate.getResource("5f58326b4681991cfcfd522f");

        String id="5f5871804681993b78119ecd";
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
       //打开流下载对象
       // GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //获取流对象
        //GridFsResource gridFsResource=new GridFsResource(gridFSFile,downloadStream);
        //获取数据
        //String s = IOUtils.toString(gridFsResource.getInputStream(),"UTF-8");

        //System.out.println("打印文件:"+s);

        //System.out.println("对象"+gridFSFile);


        //gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
    }


    @Test
    public void getFile() throws IOException {

        //根据fs.files的Id查询
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5f5871804681993b78119ecd")));
        //打开一个下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //获取流中的数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");

        System.out.println(s);

    }

}
