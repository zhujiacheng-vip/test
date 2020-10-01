package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Mr zhu
 * @Date 2020/9/11 19:35
 */
public class MqConsumer_zhujiacheng {

    //创建队列
    private static final String QUEUE = "zhujiacheng";

    public static void getConnect(String msg){

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

            channel.queueDeclare(QUEUE,true,false,false,null);

            //实现消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                //当接收到消息就会执行此方法


            };

            String s = channel.basicConsume(QUEUE, true, defaultConsumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
