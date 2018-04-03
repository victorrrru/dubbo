package com.fww.DesignMode.observerExtend;

/**
 * Created By victorrrr on 2017/8/7
 */
public class ConcreteSubject extends Subject {

    private String sujectState;

    public String getSujectState() {
        return sujectState;
    }

    public void setSujectState(String sujectState) {
        this.sujectState = sujectState;
    }
}
