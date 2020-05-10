package cn.ixan.search.example;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/4 15:05
 * @description man factory
 */
public interface ManFactory {
    Man create();
}

class WhiteManFactory implements ManFactory {

    @Override
    public Man create() {
        return new WhiteMan();
    }
}

class BlackManFactory implements ManFactory {

    @Override
    public Man create() {
        return new BlackMan();
    }
}
