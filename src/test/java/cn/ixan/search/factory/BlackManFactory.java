package cn.ixan.search.factory;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/5/10 23:09
 * @description
 */
public class BlackManFactory implements ManFactory {

    @Override
    public Man create() {
        return new BlackMan();
    }
}
