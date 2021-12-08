package com.ixan.boot.test.io;

import java.io.File;
import java.io.IOException;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/9/11 15:28
 * @description 测试文件类
 * @version 1.0
 */
public class FileTest {
	public static void main(String[] args) throws IOException {
		//1.创建文件
		File file = new File("E:\\work\\java.txt");
		if (!file.exists()) {
			boolean newFile = file.createNewFile();
			System.out.println("createNewFile:" + newFile);
		}
		//2、创建文件夹
		File file1 = new File("E:\\work\\java");
		if (!file1.exists()) {
			boolean newFile = file1.mkdir();
			System.out.println("mkdir:" + newFile);
		}
		//2、创建多级文件夹
		File file2 = new File("E:\\work\\javaWeb\\html");
		if (!file2.exists()) {
			boolean newFile = file2.mkdirs();
			System.out.println("mkdirs:" + newFile);
		}

	}
}
