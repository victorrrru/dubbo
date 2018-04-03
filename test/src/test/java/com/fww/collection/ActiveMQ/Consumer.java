package com.fww.collection.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;


/**
 * @author Administrator
 * @date 2017/12/26 17:25
 */
@SpringBootApplication
@Component
public class Consumer implements MessageListener, ExceptionListener {
    private String name = ActiveMQConnectionFactory.DEFAULT_USER;
    private String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    private String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
    private ActiveMQConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private String subject = "myQueue";
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public static Boolean isconnection=false;
    @Resource
    private BankMapper bankMapper;
    /**
     *  连接ActiveMQ
     */
    private void init() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(name,password,url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(subject);
        consumer = session.createConsumer(destination);
    }

    public void consumerMessage() throws JMSException {
        this.init();
        //设置消息监听和异常监听
        consumer.setMessageListener(this);
        connection.setExceptionListener(this);
        connection.start();
        System.out.println("消费者开始监听....");
        isconnection = true;
//        Message receive = consumer.receive();
    }

    public void close() throws JMSException {
        if(null!=consumer){
            consumer.close();
        }
        if(null!=session){
            session.close();
        }
        if(null!=connection){
            connection.close();
        }
    }
    /**
     * 异常处理函数
     */
    @Override
    public void onException(JMSException exception) {
        //发生异常关闭连接
        isconnection = false;
    }

    /**
     * 消息处理函数
     */
    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof TextMessage){
                TextMessage textMsg = (TextMessage) message;
                String text = textMsg.getText();
                Bank bank = new Bank().setNumber(text);
                bankMapper.insertSelective(bank);
                System.out.println("消费者接收到的消息======="+text);
            }else {
                System.out.println("接收的消息不符合");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
