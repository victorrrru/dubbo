package com.fww.DesignMode.decorator.test2;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Pepper extends AbstractCodiment {
    Hamburger hamburger;

    public Pepper(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + "加辣椒";
    }

    @Override
    public Double getPrice() {
        return hamburger.getPrice() + 0.5;
    }
}
