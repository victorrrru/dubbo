package com.fww.DesignMode.observer;

/**
 * Created By victorrrr on 2017/8/7
 */
public class Test {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.add(new ConcreteObserver("小明", subject));
        subject.add(new ConcreteObserver("小红", subject));
        subject.add(new ConcreteObserver("小白", subject));

        subject.setSujectState("...老板回来了");
        subject.advice();
    }

}
