package com.fww.DesignMode.decorator.test2;

/**
 * Created by victorrrr on 2017/6/7.
 */
public abstract class AbstractCodiment extends Hamburger {

    private Hamburger hamburger;

    public abstract String getName();

    public void decorator(Hamburger hamburger) {
        this.hamburger = hamburger;
    }
}
