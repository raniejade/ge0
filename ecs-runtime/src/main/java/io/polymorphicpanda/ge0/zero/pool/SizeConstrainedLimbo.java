package io.polymorphicpanda.ge0.zero.pool;

import io.polymorphicpanda.ge0.ecs.pool.Poolable;

/**
 * @author Ranie Jade Ramiso
 */
public class SizeConstrainedLimbo<T extends Poolable> extends AbstractEvictingLimbo<T> {
    private final int minimum;
    private final int limit;

    public SizeConstrainedLimbo(Limbo<T> delegate, int minimum, int limit) {
        super(delegate);

        if (minimum > limit) {
            throw new IllegalArgumentException();
        }

        this.minimum = minimum;
        this.limit = limit;
    }

    @Override
    protected void evict() {
        if (getSize() > limit) {
            while (getSize() > minimum) {
                poll();
            }
        }
    }
}
