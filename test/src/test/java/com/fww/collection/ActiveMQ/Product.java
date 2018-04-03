package com.fww.collection.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Administrator
 * @date 2017/12/26 17:09
 */
public class Product {
    private String username = ActiveMQConnectionFactory.DEFAULT_USER;
    private String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    private String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

    private Connection connection = null;
    private Session session = null;
    private String subject = "myQueue";
    private Destination destination = null;
    private MessageProducer producer = null;
    /**
     * @Description 初始化方法
     * @Author 刘俊重
     * @Date 2017/12/20
     */
    private void init() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username,password,url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(subject);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    public void productMessage(String message) throws JMSException {
        this.init();
        TextMessage textMessage = session.createTextMessage(message);
        connection.start();
        System.out.println("生产者准备发送消息："+textMessage);
        producer.send(textMessage);
        System.out.println("生产者已发送完毕消息。。。");
    }

    public void close() throws JMSException {
        System.out.println("生产者开始关闭连接");
        if(null!=producer){
            producer.close();
        }
        if(null!=session){
            session.close();
        }
        if(null!=connection){
            connection.close();
        }
    }
}
