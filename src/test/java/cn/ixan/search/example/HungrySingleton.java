package cn.ixan.search.example;

/**
 * Singleton - hungry man pattern
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
        System.out.println("-->饿汉式单例模式开始调用构造函数");
    }

    public static HungrySingleton getInstance(){
        System.out.println("-->饿汉式单例模式开始调用公有方法返回实例");
        return instance;
    }
}
