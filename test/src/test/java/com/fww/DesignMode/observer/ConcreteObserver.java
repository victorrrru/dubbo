package com.fww.DesignMode.observer;

/**
 * Created By victorrrr on 2017/8/7
 */
public class ConcreteObserver extends Observer {

    private String name;
    private String message;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        message = subject.getMessage();
        System.out.println(name + "收到推送消息：" + message);
    }
}
