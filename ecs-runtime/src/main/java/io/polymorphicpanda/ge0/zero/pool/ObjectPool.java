package io.polymorphicpanda.ge0.zero.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.pool.PooledObject;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class ObjectPool<T extends PooledObject> {

    private final Set<T> liveObjects = new HashSet<>();
    private final Deque<T> limbo = new ArrayDeque<>();

    public T request() {
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

    protected abstract T create();
}
