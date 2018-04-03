package com.fww.decorator.test1;

/**
 * Created by victorrrr on 2017/6/7.
 */
public abstract class AbstractCodiment implements Hamburger {

    Hamburger hamburger;

    public AbstractCodiment(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    public String getName() {
        return hamburger.getName();
    }

    public Double getPrice() {
        return hamburger.getPrice();
    }

}
