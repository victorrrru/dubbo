package com.fww.xmlparse;

import java.io.InputStream;
import java.util.List;

/**
 * @author 范文武
 * @date 2018/05/28 14:51
 */
public interface BookParse {
    /**
     * 解析输入流 得到Book对象集合
     * @param is
     * @return
     * @throws Exception
     */
    public List<Book> parse(InputStream is) throws Exception;

    /**
     * 序列化Book对象集合 得到XML形式的字符串
     * @param books
     * @return
     * @throws Exception
     */
    public String serialize(List<Book> books) throws Exception;
}
