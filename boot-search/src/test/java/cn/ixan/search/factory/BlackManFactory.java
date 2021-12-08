package cn.ixan.search.factory;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/5/10 23:09
 * @description
 */
public class BlackManFactory implements ManFactory {

    @Override
    public Man create() {
        return new BlackMan();
    }
}
