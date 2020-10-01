package com.xuecheng.test.freemarker.controller;

import com.xuecheng.test.freemarker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-12 18:40
 **/
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {

    @RequestMapping("/test")
    public String test1(Map<String, Object> map){

        map.put("name", "小黑");

        /*-----------两个实体类-------------*/
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMondy(1000.86f);
        stu1.setBirthday(new Date());
        /*------------------------*/
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMondy(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());

        /*----------集合---------------*/
        List<Student> friends = new ArrayList<>();
        //集合中添加实体类1
        friends.add(stu1);
        //实体类2中添加List集合
        stu2.setFriends(friends);
        //实体类2中添加实体类1
        stu2.setBestFriend(stu1);

        /*----------list集合-----------------*/
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        map.put("stus", stus);

        //创建map,实体类放入map中
        Map<String, Object> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);

        //map放入数据模型中
        map.put("stuMap", stuMap);

        return "test1";

    }

}
