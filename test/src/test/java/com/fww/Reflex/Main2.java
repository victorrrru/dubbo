package com.fww.Reflex;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2018/01/10 18:17
 */
public class Main2 {
    /**
     * 代理接口
     */
    interface ITest {
        String test(String val);
    }

    public static void main(String[] args) throws IOException {
        // 通过 ProxyGenerator.generateProxyClass 产生字节码
        byte[] testProxyBytes = ProxyGenerator.generateProxyClass("TestProxy", new Class[]{ITest.class});
        // 将字节码输出到文件，然后我们再反编译它，看看它的内容是什么
        FileOutputStream fileOutputStream = new FileOutputStream("TestProxy.class");
        fileOutputStream.write(testProxyBytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
