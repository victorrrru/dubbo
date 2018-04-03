package com.fww.DesignMode.observerExtend;

/**
 * Created By victorrrr on 2017/8/7
 */
public class ConcreteObserver extends Observer {

    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        observerState = subject.getSujectState();
        System.out.println("通知" + name + ":" + observerState);
    }
}
