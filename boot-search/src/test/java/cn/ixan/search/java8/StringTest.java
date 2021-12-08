package cn.ixan.search.java8;

import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/4/10 19:59
 * @description string test
 */
public class StringTest {
    public static void main(String[] args){
        int sum = 0;
        for(int i=0; i<10; i++){
            sum += i;
            if(i % 3 ==0){
                break;
            }
        }
        System.out.println(sum);

        float f = 1.3f;
        char c = 'c';
        byte b = -128;
        int i = 10;
    }

    @Test
    public void happy(){
        int i = 1;
        int j = i++;
        if(i==(++j)&&(i++)==j){
            i += j;
        }
        System.out.println(i);
    }

    @Test
    public void magic(){
        int i = 50;
        i = i++ * 2;
        System.out.println(i);
    }

    @Test
    public void testSystem(){
        System.out.println(System.getProperty("java.vendor"));
    }
}
