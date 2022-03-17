package com.ixan.boot.test.base.util.serial;

import java.io.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/17 11:07
 * @description ExterUser1的序列化测试
 * @version 1.0
 */
public class ExterUser1SerializableTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ExterUser1 user1 = new ExterUser1();
		user1.setName("lucy");
		user1.setAge(27);
		System.out.println(user1);
		//序列化对象到文件中去
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("template"));
		outputStream.writeObject(user1);
		outputStream.close();

		//反序列化
		File file = new File("template");
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
		ExterUser1 newUser = (ExterUser1) inputStream.readObject();
		System.out.println(newUser);
		inputStream.close();
	}
}
