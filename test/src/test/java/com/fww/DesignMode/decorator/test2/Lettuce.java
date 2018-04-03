package com.fww.DesignMode.decorator.test2;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Lettuce extends AbstractCodiment {
    Hamburger hamburger;

    public Lettuce(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public Double getPrice() {
        return hamburger.getPrice() + 1.0;
    }

    @Override
    public String getName() {
        return hamburger.getName() + "加生菜";
    }
}
