package cn.ixan.search.test;

public class FinallyTest {

    public static void main(String[] args) {
        int i = test1();
        System.out.println("test1(): "+i);
    }

    private static int test1() {
        int i = 1;
        try {
            System.out.println("try...");
            return i += 10;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch ...");
        } finally {
            i++;
            System.out.println("finally ...");
            System.out.println("i =" + i);
        }
        return i;
    }
}
