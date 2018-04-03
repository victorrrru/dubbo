package com.fww.Reflex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @date 2017/12/29 14:20
 */
public class SaleService implements Sale{
    public void clothes(int size) {
        System.out.println("衣服大小" + size);
    }

    @Override
    public void sale() {
        System.out.println("sale");
    }
}
