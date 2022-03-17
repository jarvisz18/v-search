package com.ixan.boot.test.base.util.serial;

import java.io.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/17 11:14
 * @description 静态变量的序列化测试
 * @version 1.0
 */
public class StaticSerialableTest implements Serializable {
	public static int staticVar = 5;

	public static void main(String[] args) {
		try {
			//初始化时staticVar 为5
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("result.obj"));
			outputStream.writeObject(new StaticSerialableTest());
			outputStream.close();

			//反序列化时,staticVar为10
			StaticSerialableTest.staticVar = 10;

			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("result.obj"));
			StaticSerialableTest test = (StaticSerialableTest) objectInputStream.readObject();
			System.out.println(staticVar);
			objectInputStream.close();

			System.out.println(staticVar);


		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
