package cn.ixan.search.example;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/4 14:52
 * @description idler man pattern
 */
public class IdlerSingleton {
    private static IdlerSingleton instance = null;

    private IdlerSingleton(){}

    public static IdlerSingleton getInstance(){
        if(instance == null){
            synchronized (IdlerSingleton.class){
                if( instance == null){
                    instance = new IdlerSingleton();
                }
            }
        }
        return instance;
    }
}
