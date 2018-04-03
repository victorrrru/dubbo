package com.fww.decorator.test1;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class Lettuce extends AbstractCodiment {

    public Lettuce(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public Double getPrice() {
        return super.getPrice() + 1.0;
    }

    @Override
    public String getName() {
        return super.getName() + "加生菜";
    }
}
