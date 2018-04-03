package com.fww.prototype;

/**
 * Created by victorrrr
 * Date : 2017/7/4 17:18
 */
public class Test {

    public static void main(String[] args) {
        Prototype pro = new ConcretePrototype("prototype");
        Prototype pro2 = (Prototype)pro.clone();
        System.out.println(pro.getName());
        System.out.println(pro2.getName());
    }

}
