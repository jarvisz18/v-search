package cn.ixan.search.factory;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/4 15:09
 * @description man factory test
 */
public class ManFactoryTest {
    public static void main(String[] args) {
        BlackManFactory blackManFactory = new BlackManFactory();
        WhiteManFactory whiteManFactory = new WhiteManFactory();

        Man black = blackManFactory.create();
        Man white = whiteManFactory.create();

        black.talk();
        white.talk();
    }
}
