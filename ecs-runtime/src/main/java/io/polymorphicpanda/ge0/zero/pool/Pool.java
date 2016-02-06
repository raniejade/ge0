package io.polymorphicpanda.ge0.zero.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class Pool<T extends Poolable> {

    private final Set<T> liveObjects = new HashSet<>();
    private final Deque<T> limbo = new ArrayDeque<>();

    public T request() throws InstantiationException {
        T object;

        if (!limbo.isEmpty()) {
            object = limbo.pollFirst();
        } else {
            object = create();
        }

        liveObjects.add(object);

        return object;
    }

    public void release(T object) {
        if (!liveObjects.contains(object)) {
            throw new IllegalArgumentException("Not managed!");
        }

        liveObjects.remove(object);

        object.reset();
        limbo.push(object);
    }

    protected abstract T create() throws InstantiationException;
}
