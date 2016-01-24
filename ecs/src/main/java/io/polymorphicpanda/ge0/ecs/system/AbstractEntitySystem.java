package io.polymorphicpanda.ge0.ecs.system;

import javax.annotation.Nonnegative;

import io.polymorphicpanda.ge0.ecs.World;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.event.EventBus;

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
            getWorldManager().enableSystem(this);
        } else {
            getWorldManager().disableSystem(this);
        }
    }

    public void process(float delta) {
        for (int entity: getWorldManager().entities(aspect)) {
            process(delta, entity);
        }
    }

    public void initialize() {
        // do nothing
    }

    protected abstract void process(@Nonnegative float delta, int entity);

    public void dispose() {
        // do nothing
    }

    @Deprecated
    public final void impl_setWorld(World world) {
        this.world = world;
    }

    protected final EventBus getEventBus() {
        return getWorldManager().eventBus(this);
    }


    protected final World.Manager getWorldManager() {
        return world.getManager();
    }
}
