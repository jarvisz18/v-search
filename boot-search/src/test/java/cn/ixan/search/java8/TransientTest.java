package cn.ixan.search.java8;

import java.io.*;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/2/7 14:15
 * @description transient用法测试
 */
public class TransientTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("zhangxianlong");
        user.setPassword("123456");
        System.out.println("read before serializable");
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D://user.txt"));
            outputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            // 在反序列化之前改变username的值
            User.username = "jmwang";
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D://user.txt"));
            User object = (User) inputStream.readObject();
            inputStream.close();
            System.out.println("read after serializable");
            System.out.println("username:"+object.getUsername());
            System.out.println("password:"+object.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
