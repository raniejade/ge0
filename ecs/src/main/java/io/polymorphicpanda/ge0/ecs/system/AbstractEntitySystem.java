package io.polymorphicpanda.ge0.ecs.system;

import java.util.concurrent.atomic.AtomicBoolean;

import io.polymorphicpanda.ge0.ecs.World;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractEntitySystem {
    private final AtomicBoolean active = new AtomicBoolean(true);
    private final Aspect.Builder aspect;
    private World world;

    protected AbstractEntitySystem(Aspect.Builder aspect) {
        this.aspect = aspect;
    }

    public final void setActive(boolean active) {
        this.active.set(active);
    }

    public final boolean isActive() {
        return active.get();
    }

    public final void process(float delta) {
        for (int entity: getWorld().entities(aspect)) {
            process(delta, entity);
        }
    }

    public void initialize() {
        // do nothing
    }

    protected abstract void process(float delta, int entity);

    public void dispose() {
        // do nothing
    }

    @Deprecated
    public final void impl_setWorld(World world) {
        this.world = world;
    }

    public final World getWorld() {
        return world;
    }
}
