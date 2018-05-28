package com.fww.xmlparse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 范文武
 * @date 2018/05/28 16:25
 */
public class Dom4jParser {
    public static void main(String[] args) {
        File file = new File("books.xml");
        List<Book> books = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document doc = saxReader.read(file);
            Element element = doc.getRootElement();
            Iterator parent = element.elementIterator();
            while (parent.hasNext()) {
                Book book = new Book();
                Element parentEle = (Element) parent.next();
                Iterator sub = parentEle.elementIterator();
                while (sub.hasNext()) {
                    Element subEle = (Element) sub.next();
                    if ("id".equals(subEle.getQName().getName())) {
                        book.setId(Integer.valueOf(subEle.getText()));
                    }
                    if ("name".equals(subEle.getQName().getName())) {
                        book.setName(subEle.getText());
                    }
                    if ("price".equals(subEle.getQName().getName())) {
                        book.setPrice(Float.valueOf(subEle.getText()));
                    }
                }
                books.add(book);
            }
            System.out.println(books);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
