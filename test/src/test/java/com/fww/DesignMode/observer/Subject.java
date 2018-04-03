package com.fww.DesignMode.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created By victorrrr on 2017/8/7
 */
public abstract class Subject {

    private List<Observer> observers = new LinkedList();

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
