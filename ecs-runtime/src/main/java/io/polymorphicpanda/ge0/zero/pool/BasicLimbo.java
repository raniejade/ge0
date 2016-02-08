package io.polymorphicpanda.ge0.zero.pool;

import java.util.ArrayDeque;
import java.util.Deque;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public class BasicLimbo<T extends Poolable> implements Limbo<T> {
    private final Deque<T> deque = new ArrayDeque<>();

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public int getSize() {
        return deque.size();
    }

    @Override
    public void push(T object) {
        deque.push(object);
    }

    @Override
    public T poll() {
        return deque.pollFirst();
    }

    @Override
    public T peek() {
        return deque.peekFirst();
    }
}
