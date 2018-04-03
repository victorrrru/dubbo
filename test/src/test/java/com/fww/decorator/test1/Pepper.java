package com.fww.decorator.test1;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Pepper extends AbstractCodiment {

    public Pepper(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getName() {
        return super.getName() + "加辣椒";
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 0.5;
    }
}
