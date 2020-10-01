package com.xuecheng.manage_cms.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

/**
 * @Author Mr zhu
 * @Date 2020/9/9 15:25
 */
@Configuration
public class MongoConfig {

    //从配置文件里面拿到属性对db赋值
    @Value("${spring.data.mongodb.database}")
    private String db;

    @Bean
    public GridFSBucket getGridFSBucket(MongoClient mongoClient){
        //连接数据库
        MongoDatabase database = mongoClient.getDatabase(db);
        //创建GridFSBucket
        GridFSBucket bucket = GridFSBuckets.create(database);
        return bucket;
    }

}
