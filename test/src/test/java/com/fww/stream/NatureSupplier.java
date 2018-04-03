package com.fww.stream;

import java.util.function.Supplier;

/**
 * Created By victorrrr on 2017/8/9
 */
public class NatureSupplier implements Supplier {
    long a = 0;
    @Override
    public Object get() {
        this.a = this.a + 1 ;
        return a;
    }
}
