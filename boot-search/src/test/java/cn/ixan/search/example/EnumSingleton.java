package cn.ixan.search.example;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/9 16:51
 * @description EnumSingleton
 */
public enum EnumSingleton {
    INSTANCE,
    ANOTHER;
    public void add(){
        System.out.println("add method ...");
    }
}
