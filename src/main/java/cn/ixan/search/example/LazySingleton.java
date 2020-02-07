package cn.ixan.search.example;

/**
 * Singleton - lazy man pattern
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton(){
        System.out.println("-->懒汉式单例模式开始调用构造函数");
    }

    public static LazySingleton getInstance(){
        System.out.println("-->懒汉式单例模式开始调用公有方法返回实例");
        if(lazySingleton == null){
            System.out.println("-->懒汉式构造函数的实例当前并没有被创建");
            lazySingleton = new LazySingleton();
        } else {
            System.out.println("-->懒汉式构造函数的实例已经被创建");
        }
        System.out.println("-->方法调用结束,返回实例");
        return lazySingleton;
    }
}
