package com.fww.decorator.test1;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Ketchup extends AbstractCodiment {

    public Ketchup(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getName() {
        return super.getName() + "加番茄酱";
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 1.0;
    }
}
