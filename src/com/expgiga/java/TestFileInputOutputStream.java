package com.expgiga.java;

import org.testng.annotations.Test;

import java.io.*;

/*
 * 1.流的分类
 * 按照流向的不同：输入流和输出流
 * 按照处理数据的单位不同：字节流和字符流(处理的是文本文件)
 * 按照角色的不同：节点流(直接作用在文件上的)和处理流
 *
 * 2.IO流的体系：
 * 抽象基类         节点流(文件流)
 * InputStream     FileInputStream
 * OutputStream    FileOutputStream
 * Reader          FileReader
 * Writer          FileWriter
 *
 * 流的分类：
 * 按操作数据单位的不同分为：字节流(8 bit)，字符流(16 bit)
 * 按流的角色的不同分为：节点流 处理流
 *
 * 节点流：是最基本的，直接是作用在文件上的。
 * FileInputStream FileOutputStream (字节流)
 * FileReader FileWriter (字符流)
 *
 * 处理流：
 * 转换流：将字节流转换为字符流InputStreamReader OutputStreamWriter
 * 缓冲流：BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter
 *
 * Java的IO流共涉及40多个类，实际上非常的规则，都是从这四个抽象基类派生出来的。
 * 输入流 InputStream(字节流)  Reader(字符流)
 * 输出流 OutputStream(字节流) Writer(字符流)
 *
 */
public class TestFileInputOutputStream {
    //从硬盘存在的一个文件中读取其内容到程序中，使用FileInputStream
    @Test
    public void testFileInputStream1() throws IOException {
        //1.创建一个File类的对象
        File file = new File("hello.txt");
        //2.创建一个FileInputStream类的对象
        FileInputStream fis = new FileInputStream(file);
        //3.调用FileInputStream的方法，实现File文件的读取
        /*
         * read()：读取文件的一个字节，当执行到文件的结尾时，返回-1
         */
//        int b = fis.read();
//        while (-1 != b) {
//            System.out.print(b);//ASCII
//            System.out.print((char) b);
//            b = fis.read();
//        }
        int b;
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }
        //4.关闭相应的流
        fis.close();
    }

    //使用try-catch的方式处理如下的异常，更合理：保证流的关闭操作，一定能够执行
    @Test
    public void testFileInputStream2() {
        FileInputStream fis = null;
        try {
            //1.创建一个File类的对象
            File file = new File("hello.txt");
            //2.创建一个FileInputStream类的对象
            fis = new FileInputStream(file);
            //3.调用FileInputStream的方法，实现File文件的读取
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭相应的流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileInputStream3() {
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");
            fis = new FileInputStream(file);
            byte[] b = new byte[20]; //要读取到的数据写入数组
            int len; //每次读入到byte中的字节的长度
            while ((len = fis.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print((char) b[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}