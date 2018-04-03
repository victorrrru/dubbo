package com.fww.Reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @date 2017/12/29 14:31
 */
public class TestProxy {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Sale proxy = (Sale) Proxy.newProxyInstance(SaleService.class.getClassLoader(), SaleService.class.getInterfaces(),
                (proxy1, method, args1) -> {
                    System.out.println("before");
                    Object invoke = method.invoke(SaleService.class.newInstance(), args1);
                    System.out.println("end");
                    return invoke;
                });
        proxy.clothes(40);
        proxy.sale();
        System.out.println("------------");
//        Class<SaleService> aClass = SaleService.class;
//        Class<?> aClass = Class.forName("com.example.demo.Reflex.SaleService");
//        Object o = aClass.newInstance();
//        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            Object invoke = method.invoke(o);
//            System.out.println(invoke);
//        }
    }
}
