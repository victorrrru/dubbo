package com.fww.DesignMode.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created By victorrrr on 2017/8/7
 */
public class ConcreteSubject extends Subject{
    private List<Observer> observers = new LinkedList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String sujectState) {
        this.message = sujectState;
        System.out.println("发布消息：" + sujectState);
        this.advice();
    }
    /**
     * 增加观察者
     * @param observer
     */
    public void add(Observer observer) {
        observers.add(observer);
    }

    /**
     * 删除观察者
     * @param observer
     */
    public void delete(Observer observer) {
        observers.remove(observer);
    }

    public void advice(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
