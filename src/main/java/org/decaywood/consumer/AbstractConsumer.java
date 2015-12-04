package org.decaywood.consumer;

import org.decaywood.AbstractService;
import org.decaywood.timeWaitingStrategy.TimeWaitingStrategy;
import org.decaywood.utils.URLMapper;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: decaywood
 * @date: 2015/11/30 22:22.
 */
public abstract class AbstractConsumer<T> extends AbstractService implements Consumer<T>, Function<T, T> {

    public AbstractConsumer() {
        this(null);
    }

    public AbstractConsumer(TimeWaitingStrategy strategy) {
        this(strategy, URLMapper.MAIN_PAGE.toString());
    }

    public AbstractConsumer(TimeWaitingStrategy strategy, String webSite) {
        super(strategy, webSite);
    }

    protected abstract void consumLogic(T t) throws Exception;

    @Override
    public void accept(T t) {
        try {
            consumLogic(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T apply(T t) {
        accept(t);
        return t;
    }

}
