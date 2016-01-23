package io.polymorphicpanda.ge0.ecs.system;

import io.polymorphicpanda.ge0.ecs.World;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractEntitySystem {
    private final Aspect.Builder aspect;
    private World world;

    protected AbstractEntitySystem(Aspect.Builder aspect) {
        this.aspect = aspect;
    }

    protected void setEnabled(boolean enabled) {
        if (enabled) {
            getWorld().enableSystem(this);
        } else {
            getWorld().disableSystem(this);
        }
    }

    public void process(float delta) {
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

    protected final World getWorld() {
        return world;
    }
}
