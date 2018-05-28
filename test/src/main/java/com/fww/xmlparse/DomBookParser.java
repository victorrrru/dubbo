package com.fww.xmlparse;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 范文武
 * @date 2018/05/28 16:13
 */
public class DomBookParser implements BookParse {
    @Override
    public List<Book> parse(InputStream is) throws Exception {
        List<Book> books = new ArrayList<>();
        //取得DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //从factory获取DocumentBuilder实例
        DocumentBuilder builder = factory.newDocumentBuilder();
        //解析输入流 得到Document实例
        Document doc = builder.parse(is);
        Element rootElement = doc.getDocumentElement();
        NodeList items = rootElement.getElementsByTagName("book");
        for (int i = 0; i < items.getLength(); i++) {
            Book book = new Book();
            Node item = items.item(i);
            NodeList properties = item.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
                Node property = properties.item(j);
                String nodeName = property.getNodeName();
                if ("id".equals(nodeName)) {
                    book.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));
                } else if ("name".equals(nodeName)) {
                    book.setName(property.getFirstChild().getNodeValue());
                } else if ("price".equals(nodeName)) {
                    book.setPrice(Float.parseFloat(property.getFirstChild().getNodeValue()));
                }
            }
            books.add(book);
        }
        return books;
    }

    @Override
    public String serialize(List<Book> books) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //由builder创建新文档
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("books");

        for (Book book : books) {
            Element bookElement = doc.createElement("book");
            bookElement.setAttribute("id", book.getId() + "");

            Element nameElement = doc.createElement("name");
            nameElement.setTextContent(book.getName());
            bookElement.appendChild(nameElement);

            Element priceElement = doc.createElement("price");
            priceElement.setTextContent(book.getPrice() + "");
            bookElement.appendChild(priceElement);

            rootElement.appendChild(bookElement);
        }

        doc.appendChild(rootElement);
        //取得TransformerFactory实例
        TransformerFactory transFactory = TransformerFactory.newInstance();
        //从transFactory获取Transformer实例
        Transformer transformer = transFactory.newTransformer();
        // 设置输出采用的编码方式
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 是否自动添加额外的空白
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // 是否忽略XML声明
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        StringWriter writer = new StringWriter();
        //表明文档来源是doc
        Source source = new DOMSource(doc);
        //表明目标结果为writer
        Result result = new StreamResult(writer);
        //开始转换
        transformer.transform(source, result);

        return writer.toString();
    }

    @Test
    public void test() {
        InputStream is = this.getClass().getResourceAsStream("/book.xml");
        //创建SaxBookParser实例
        DomBookParser parser = new DomBookParser();
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
}
