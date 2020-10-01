package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Mr zhu
 * @Date 2020/9/11 19:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MqProducer_zhujiacheng {

    //创建队列
    private static final String QUEUE = "zhujiacheng";

    @Test
    public void sendMsg() throws IOException {

getConnect("我是小猪");

    }


    public static void getConnect(String msg) throws IOException {

        //创建RabbitMq的连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");

        connectionFactory.setPort(5672);

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("guest");

        //设置虚拟机，一个mq可以设置多个虚拟机，每个虚拟机相当于一个独立的服务
        connectionFactory.setVirtualHost("/");

        Connection connection = null;

        try {
            //建立连接
            connection = connectionFactory.newConnection();

            //创建通道
            Channel channel = connection.createChannel();

            //声明队列，如果队列在mq里,就不用创建

            /*参数明细
            1,queue队列名称
            2,durable 是否持久化，如果持久化，mq重启后队列还在
            *
            * */

            //创建队列，
            channel.queueDeclare(QUEUE,true,false,false,null);

            channel.basicPublish("",QUEUE,null,msg.getBytes());

            System.out.println("send to mq"+msg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            connection.close();

        }

    }

}
