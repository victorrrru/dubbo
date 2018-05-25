package com.fww.DesignMode.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created By victorrrr on 2017/8/7
 */
public abstract class Subject {
    abstract void add(Observer observer);
    abstract void delete(Observer observer);
    abstract void advice();
}
