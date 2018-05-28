package com.fww.xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 范文武
 * @date 2018/05/28 14:36
 */
public class MyHandler extends DefaultHandler {
    private List<Book> books;
    private Book book;
    private StringBuilder builder;

    //返回解析后得到的Book对象集合
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        books = new ArrayList<>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("book".equals(qName)) {
            book = new Book();
        }
        builder.setLength(0);   //将字符长度设置为0 以便重新开始读取元素内的字符节点
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);  //将读取的字符数组追加到builder中
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("id".equals(qName)) {
            book.setId(Integer.parseInt(builder.toString()));
        } else if ("name".equals(qName)) {
            book.setName(builder.toString());
        } else if ("price".equals(qName)) {
            book.setPrice(Float.parseFloat(builder.toString()));
        } else if ("book".equals(qName)) {
            books.add(book);
        }
    }
}
