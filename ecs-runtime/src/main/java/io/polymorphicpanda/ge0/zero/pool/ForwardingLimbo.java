package io.polymorphicpanda.ge0.zero.pool;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public class ForwardingLimbo<T extends Poolable> implements Limbo<T> {
    private final Limbo<T> delegate;

    public ForwardingLimbo(Limbo<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public void push(T object) {
        delegate.push(object);
    }

    @Override
    public T poll() {
        return delegate.poll();
    }

    @Override
    public T peek() {
        return delegate.peek();
    }
}
