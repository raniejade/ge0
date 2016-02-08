package io.polymorphicpanda.ge0.zero.pool;

import java.util.HashSet;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * TODO: objects in limbo can pile-up, implement time-based eviction (idle time).
 *
 * @author Ranie Jade Ramiso
 */
public abstract class Pool<T extends Poolable> {

    private final Set<T> liveObjects = new HashSet<>();
    private final Limbo<T> limbo;

    protected Pool(Limbo<T> limbo) {
        this.limbo = limbo;
    }

    public T request() throws InstantiationException {
        T object;

        if (!limbo.isEmpty()) {
            object = limbo.poll();
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
