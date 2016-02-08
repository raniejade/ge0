package io.polymorphicpanda.ge0.zero.pool;

import java.util.concurrent.atomic.AtomicBoolean;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractEvictingLimbo<T extends Poolable> extends ForwardingLimbo<T> {
    private final AtomicBoolean evicting = new AtomicBoolean(false);

    public AbstractEvictingLimbo(Limbo<T> delegate) {
        super(delegate);
    }

    @Override
    public boolean isEmpty() {
        process();
        return super.isEmpty();
    }

    @Override
    public void push(T object) {
        process();
        super.push(object);
    }

    @Override
    public T poll() {
        process();
        return super.poll();
    }

    private void process() {
        if (!evicting.get()) {
            evicting.set(true);
            evict();
            evicting.set(false);
        }
    }

    protected abstract void evict();
}
