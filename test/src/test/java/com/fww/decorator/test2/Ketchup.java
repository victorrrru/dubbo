package com.fww.decorator.test2;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Ketchup extends AbstractCodiment {
    Hamburger hamburger;

    public Ketchup(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + "加番茄酱";
    }

    @Override
    public Double getPrice() {
        return hamburger.getPrice() + 1.0;
    }
}
