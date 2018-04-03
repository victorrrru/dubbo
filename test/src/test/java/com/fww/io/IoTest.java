package com.fww.io;

import org.junit.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created By victorrrr on 2017/8/12
 */
public class IoTest {
    private String str = "C:\\Users\\Administrator\\Desktop";
    /**
     * 文件处理示例
     */
    @Test
    public void createFile() {
        File f = new File(str + "/SS/P.txt");
        try {
            f.createNewFile();  //当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
            System.out.println("该分区大小" + f.getTotalSpace() / (1024 * 1024 * 1024) + "G"); //返回由此抽象路径名表示的文件或目录的名称。
//            f.mkdirs();  //创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
//            f.delete(); //  删除此抽象路径名表示的文件或目录
            System.out.println("文件名  " + f.getName());  //  返回由此抽象路径名表示的文件或目录的名称。
            System.out.println("文件父目录字符串 " + f.getParent());// 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null。

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     */
    @Test
    public void testFileInputStream() {
        int count = 0;  //统计文件字节长度
        InputStream streamReader = null;   //文件输入流
        try {
            streamReader = new FileInputStream(new File(str + "/cfd.jpg"));
          /*1.new File()里面的文件地址也可以写成D:\\David\\Java\\java 高级进阶\\files\\tiger.jpg,前一个\是用来对后一个
           * 进行转换的，FileInputStream是有缓冲区的，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。
          */
            while (streamReader.read() != -1) {  //读取文件字节，并递增指针到下一个字节
                count++;
            }
            System.out.println("---长度是： " + count + " 字节");
        } catch (final IOException e) {
            //TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                streamReader.close();
            } catch (IOException e) {
                //TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    /**
     * 字节流缓存
     * Java I/O默认是不缓冲流的，所谓“缓冲”就是先把从流中得到的一块字节序列暂存在一个被称为buffer的内部字节数组里，
     * 然后你可以一下子取到这一整块的字节数据，没有缓冲的流只能一个字节一个字节读，效率孰高孰低一目了然。
     * 有两个特殊的输入流实现了缓冲功能，一个是我们常用的BufferedInputStream.
     */
    @Test
    public void testBufferInputStream() {
        // TODO自动生成的方法存根
        byte[] buffer = new byte[512];   //一次取出的字节数大小,缓冲区大小
        int numberRead = 0;
        FileInputStream input = null;
        FileOutputStream out = null;
        try {
            input = new FileInputStream(str + "/cfd.jpg");
            out = new FileOutputStream(str + "/cfd2.jpg"); //如果文件不存在会自动创建

            while ((numberRead = input.read(buffer)) != -1) {  //numberRead的目的在于防止最后一次读取的字节小于buffer长度，
                out.write(buffer, 0, numberRead);       //否则会自动被填充0
            }
        } catch (final IOException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        }finally {
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    /**
     * 读写对象：ObjectInputStream 和ObjectOutputStream ，该流允许读取或写入用户自定义的类，
     * 但是要实现这种功能，被读取和写入的类必须实现Serializable接口，其实该接口并没有什么方法，可能相当于一个标记而已，但是确实不合缺少的。
     */
    @Test
    public void testObjectOutputStream() {
        // TODO自动生成的方法存根
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;

        try {
            objectwriter = new ObjectOutputStream(new FileOutputStream(str + "/student.txt"));
            objectwriter.writeObject(new Student("gg", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));
            objectreader = new ObjectInputStream(new FileInputStream(str + "/student.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        }finally{
            try {
                objectreader.close();
                objectwriter.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }
    }

    /**
     * 有时没有必要存储整个对象的信息，而只是要存储一个对象的成员数据，成员数据的类型假设都是Java的基本数据类型，
     * 这样的需求不必使用到与Object输入、输出相关的流对象，可以使用DataInputStream、DataOutputStream来写入或读出数据。
     * 下面是一个例子：（DataInputStream的好处在于在从文件读出数据时，不用费心地自行判断读入字符串时或读入int类型时何时将停止，
     * 使用对应的readUTF()和readInt()方法就可以正确地读入完整的类型数据。）
     */
    @Test
    public void testDataOutputStream() {
        String[] args = new String[6];
        Student[] members = {new Student("Justin",90),
                            new Student("momor",95),
                            new Student("Bush",88)};
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(str + "/student.txt"));

            for(Student member : members) {
                //写入UTF字符串
                dataOutputStream.writeUTF(member.getName());
                //写入int数据
                dataOutputStream.writeInt(member.getAge());
            }

            //所有数据至目的地to
            dataOutputStream.flush();
            //关闭流
            dataOutputStream.close();

            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(str + "/student.txt"));

            //读出数据并还原为对象
            for (int i = 0; i < members.length; i++) {
                //读出UTF字符串
                String name = dataInputStream.readUTF();
                //读出int数据
                int score = dataInputStream.readInt();
                members[i] = new Student(name,score);
            }

            //关闭流
            dataInputStream.close();

            //显示还原后的数据
            for(Student member : members) {
                System.out.printf("%s\t%d%n", member.getName(), member.getAge());
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PushbackInputStream类继承了FilterInputStream类是iputStream类的修饰者。
     * 提供可以将数据插入到输入流前端的能力(当然也可以做其他操作)。
     * 简而言之PushbackInputStream类的作用就是能够在读取缓冲区的时候提前知道下一个字节是什么，其实质是读取到下一个字符后回退的做法，
     * 这之间可以进行很多操作，这有点向你把读取缓冲区的过程当成一个数组的遍历，遍历到某个字符的时候可以进行的操作，
     * 当然，如果要插入，能够插入的最大字节数是与推回缓冲区的大小相关的，插入字符肯定不能大于缓冲区吧！
     */
    @Test
    public void testPushbackInputStream() {
        String str = "hello,rollenholt";
        PushbackInputStream push = null; // 声明回退流对象
        ByteArrayInputStream bat = null; // 声明字节数组流对象
        bat = new ByteArrayInputStream(str.getBytes());
        push = new PushbackInputStream(bat); // 创建回退流对象，将拆解的字节数组流传入
        int temp = 0;
        try {
            while ((temp = push.read()) != -1) { // push.read()逐字节读取存放在temp中，如果读取完成返回-1
                if (temp == ',') { // 判断读取的是否是逗号
                    push.unread(temp); //回到temp的位置
                    temp = push.read(); //接着读取字节
                    System.out.print("(回退" + (char) temp + ") "); // 输出回退的字符
                } else {
                    System.out.print((char) temp); // 否则输出字符
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                push.close();
                bat.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 有些情况下，当我们需要从多个输入流中向程序读入数据。此时，可以使用合并流，将多个输入流合并成一个SequenceInputStream流对象。
     * SequenceInputStream会将与之相连接的流集组合成一个输入流并从第一个输入流开始读取，直到到达文件末尾，
     * 接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。 合并流的作用是将多个源合并合一个源。
     * 其可接收枚举类所封闭的多个字节流对象。
     */
    @Test
    public void testSequenceInputStream() {
        // 创建一个合并流的对象
        SequenceInputStream sis = null;
        // 创建输出流。
        BufferedOutputStream bos = null;
        try {
            // 构建流集合。
            Vector<InputStream> vector = new Vector<InputStream>();
            vector.addElement(new FileInputStream(str + "/test1.txt"));
            vector.addElement(new FileInputStream(str + "/test2.txt"));
            vector.addElement(new FileInputStream(str + "/test3.txt"));
            Enumeration<InputStream> e = vector.elements();

            sis = new SequenceInputStream(e);

            bos = new BufferedOutputStream(new FileOutputStream(str + "/test4.txt"));
            // 读写数据
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
                bos.write(buf, 0, len);
//                bos.flush();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sis != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * PrintWriter
     */
    @Test
    public void testPrintWriter() {
        // TODO自动生成的方法存根
        char[] buffer = new char[512];   //一次取出的字节数大小,缓冲区大小
        int numberRead = 0;
        FileReader reader = null;        //读取字符文件的流
        PrintWriter writer = null;    //写字符到控制台的流

        try {
            reader = new FileReader(str + "/test1.txt");
            writer = new PrintWriter(System.out);  //PrintWriter可以输出字符到文件，也可以输出到控制台
//            writer = new PrintWriter(new FileWriter("C:/Users/think/Desktop/test5.txt"));
            while ((numberRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, numberRead);
            }
        } catch (IOException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }
            writer.close();       //这个不用抛异常
        }

    }

    /**
     * 包装类进行文件级联操作
     */
    @Test
    public void testBufferedWriter() {
        String str1;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        Vector<String> fileName = new Vector<>();
        fileName.addElement(str + "/test1.txt");
        fileName.addElement(str + "/test2.txt");
        fileName.addElement(str + "/test3.txt");
        fileName.addElement(str + "/test4.txt");

        //构建对该文件您的输入流
        try {
            writer = new BufferedWriter(new FileWriter(str + "/test6.txt"));
            for(String name : fileName){
                reader = new BufferedReader(new FileReader(name));

                while ((str1 = reader.readLine()) != null) {
                    writer.write(str1);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 使用StreamTokenizer来统计文件中的字符数
     * StreamTokenizer 类获取输入流并将其分析为“标记”，允许一次读取一个标记。
     * 分析过程由一个表和许多可以设置为各种状态的标志控制。
     * 该流的标记生成器可以识别标识符、数字、引用的字符串和各种注释样式。
     *
     *  默认情况下，StreamTokenizer认为下列内容是Token: 字母、数字、除C和C++注释符号以外的其他符号。
     *  如符号"/"不是Token，注释后的内容也不是，而"\"是Token。单引号和双引号以及其中的内容，只能算是一个Token。
     *  统计文章字符数的程序，不是简单的统计Token数就万事大吉，因为字符数不等于Token。按照Token的规定，
     *  引号中的内容就算是10页也算一个Token。如果希望引号和引号中的内容都算作Token，应该调用下面的代码：
     *    st.ordinaryChar('\'');
     * st.ordinaryChar('\"');
     */
    @Test
    public void testStreamTokenizer() {
        String fileName = str + "/test6.txt";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            //创建分析给定字符流的标记生成器
            StreamTokenizer st = new StreamTokenizer(new BufferedReader(
                    fileReader));

            //ordinaryChar方法指定字符参数在此标记生成器中是“普通”字符。
            //下面指定单引号、双引号和注释符号是普通字符
            st.ordinaryChar('\'');
            st.ordinaryChar('\"');
            st.ordinaryChar('/');

            String s;
            int numberSum = 0;
            int wordSum = 0;
            int symbolSum = 0;
            int total = 0;
            //nextToken方法读取下一个Token.
            //TT_EOF指示已读到流末尾的常量。
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                //在调用 nextToken 方法之后，ttype字段将包含刚读取的标记的类型
                switch (st.ttype) {
                    //TT_EOL指示已读到行末尾的常量。
                    case StreamTokenizer.TT_EOL:
                        break;
                    //TT_NUMBER指示已读到一个数字标记的常量
                    case StreamTokenizer.TT_NUMBER:
                        //如果当前标记是一个数字，nval字段将包含该数字的值
                        s = String.valueOf((st.nval));
                        System.out.println("数字有：" + s);
                        numberSum ++;
                        break;
                    //TT_WORD指示已读到一个文字标记的常量
                    case StreamTokenizer.TT_WORD:
                        //如果当前标记是一个文字标记，sval字段包含一个给出该文字标记的字符的字符串
                        s = st.sval;
                        System.out.println("单词有： " + s);
                        wordSum ++;
                        break;
                    default:
                        //如果以上3中类型都不是，则为英文的标点符号
                        s = String.valueOf((char) st.ttype);
                        System.out.println("标点有： " + s);
                        symbolSum++;
                }
            }
            System.out.println("数字有 " + numberSum + "个");
            System.out.println("单词有 " + wordSum + "个");
            System.out.println("标点符号有： " + symbolSum + "个");
            total = symbolSum + numberSum + wordSum;
            System.out.println("Total = " + total);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e1) {
                }
            }
        }

}
}
