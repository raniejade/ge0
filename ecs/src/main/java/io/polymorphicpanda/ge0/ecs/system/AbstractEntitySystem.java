package io.polymorphicpanda.ge0.ecs.system;

import java.util.concurrent.atomic.AtomicBoolean;

import io.polymorphicpanda.ge0.ecs.entity.Family;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractEntitySystem {
    private final AtomicBoolean active = new AtomicBoolean(true);
    protected final Family.Builder builder;

    protected AbstractEntitySystem(Family.Builder builder) {
        this.builder = builder;
    }

    public final void setActive(boolean active) {
        this.active.set(active);
    }

    public final boolean isActive() {
        return active.get();
    }

    public final void process(float delta) {
        for (int entity: builder.entities()) {
            process(delta, entity);
        }
    }

    public abstract void initialize();

    protected abstract void process(float delta, int entity);
}
