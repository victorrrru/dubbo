package com.fww.DesignMode.prototype;

/**
 * Created by victorrrr
 * Date : 2017/7/4 17:18
 */
public class Test {

    public static void main(String[] args) {
        Prototype pro = new ConcretePrototype("prototype");
        Prototype pro2 = (Prototype)pro.clone();
        System.out.println(pro);
        System.out.println(pro2);
    }

}
