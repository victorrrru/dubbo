package com.fww.collection.ActiveMQ;

import javax.jms.JMSException;

/**
 * @author Administrator
 * @date 2017/12/26 17:33
 */
public class TestProduct {
    public static void main(String[] args) throws JMSException {
        for(int i=0;i<100000;i++){
            Product product = new Product();
            product.productMessage("Hello World!"+i);
            product.close();
        }
    }
}
