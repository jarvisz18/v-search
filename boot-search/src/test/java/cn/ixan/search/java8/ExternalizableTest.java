package cn.ixan.search.java8;

import java.io.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/7 14:38
 * @description ExternalizableTest
 */
public class ExternalizableTest implements Externalizable {
    private transient String content = "是的，我将会被序列化，不管我是否被transient关键字修饰";
    private transient String username = "张贤龙是的，我将会被序列化，不管我是否被transient关键字修饰";
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
        out.writeObject(username);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content = (String)in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExternalizableTest test = new ExternalizableTest();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d://user.txt"));
        outputStream.writeObject(test);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("d://user.txt"));
        ExternalizableTest object = (ExternalizableTest) inputStream.readObject();
        System.out.println(object.content);
        System.out.println(object.username);
    }
}
