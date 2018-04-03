package com.fww.Reflex;

import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @date 2017/12/29 14:22
 */
public class ProxySale {
    public static <T> T getProxy() {
        return (T)Proxy.newProxyInstance(SaleService.class.getClassLoader(), SaleService.class.getInterfaces(), (proxy, method, args)->{
            int price = (int) method.invoke(SaleService.class.newInstance(), args);
            return price;
        });
    }
}
