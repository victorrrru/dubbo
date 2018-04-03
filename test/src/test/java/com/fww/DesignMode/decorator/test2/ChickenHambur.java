package com.fww.DesignMode.decorator.test2;

/**
 * Created by victorrrr on 2017/6/7.
 */
public class ChickenHambur extends Hamburger {

    @Override
    public String getName() {
        return "鸡肉堡";
    }

    @Override
    public Double getPrice() {
        return 10.0;
    }
}
