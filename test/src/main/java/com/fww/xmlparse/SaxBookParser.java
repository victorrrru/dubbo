package com.fww.xmlparse;

import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * @author 范文武
 * @date 2018/05/28 14:51
 */
public class SaxBookParser implements BookParse {

    @Override
    public List<Book> parse(InputStream is) throws Exception {
        //取得SAXParserFactory实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //从factory获取SAXParser实例
        SAXParser parser = factory.newSAXParser();
        //实例化自定义Handler
        MyHandler handler = new MyHandler();
        //根据自定义Handler规则解析输入流
        parser.parse(is, handler);
        return handler.getBooks();
    }

    @Override
    public String serialize(List<Book> books) throws Exception {
        //取得SAXTransformerFactory实例
        SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();
        //从factory获取TransformerHandler实例
        TransformerHandler handler = factory.newTransformerHandler();
        //从handler获取Transformer实例
        Transformer transformer = handler.getTransformer();
        // 设置输出采用的编码方式
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 是否自动添加额外的空白
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // 是否忽略XML声明
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        handler.setResult(result);
        //代表命名空间的URI 当URI无值时 须置为空字符串
        String uri = "";
        //命名空间的本地名称(不包含前缀) 当没有进行命名空间处理时 须置为空字符串
        String localName = "";

        handler.startDocument();
        handler.startElement(uri, localName, "books", null);
        //负责存放元素的属性信息
        AttributesImpl attrs = new AttributesImpl();
        char[] ch;
        for (Book book : books) {
            //清空属性列表
            attrs.clear();
            //添加一个名为id的属性(type影响不大,这里设为string)
            attrs.addAttribute(uri, localName, "id", "string", String.valueOf(book.getId()));
            //开始一个book元素 关联上面设定的id属性
            handler.startElement(uri, localName, "book", attrs);

            //开始一个id元素 没有属性
            //handler.startElement(uri, localName, "book", null);
            //handler.startElement(uri, localName, "id", null);
            //ch = String.valueOf(book.getId()).toCharArray();
            //handler.characters(ch, 0, ch.length);
            //handler.endElement(uri, localName, "id");

            //开始一个name元素 没有属性
            handler.startElement(uri, localName, "name", null);
            ch = String.valueOf(book.getName()).toCharArray();
            //设置name元素的文本节点
            handler.characters(ch, 0, ch.length);
            handler.endElement(uri, localName, "name");

            //开始一个price元素 没有属性
            handler.startElement(uri, localName, "price", null);
            ch = String.valueOf(book.getPrice()).toCharArray();
            //设置price元素的文本节点
            handler.characters(ch, 0, ch.length);
            handler.endElement(uri, localName, "price");

            handler.endElement(uri, localName, "book");
        }
        handler.endElement(uri, localName, "books");
        handler.endDocument();

        return writer.toString();
    }

    @Test
    public void test() {
        InputStream is = this.getClass().getResourceAsStream("/book.xml");
        //创建SaxBookParser实例
        SaxBookParser parser = new SaxBookParser();
        //解析输入流
        try {
            List<Book> books = parser.parse(is);
            System.out.println(books.toString());
            System.out.println("-------------");
            //序列化
            String xml = parser.serialize(books);
            FileOutputStream fos = new FileOutputStream("new_book.xml");
            fos.write(xml.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
