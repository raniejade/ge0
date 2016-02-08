package io.polymorphicpanda.ge0.zero.pool;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public interface Limbo<T extends Poolable> {
    boolean isEmpty();

    int getSize();

    void push(T object);

    T poll();

    T peek();
}
