package io.polymorphicpanda.ge0.ecs.system;

import javax.annotation.Nonnegative;

import io.polymorphicpanda.ge0.ecs.World;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.event.EventBus;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractSystem {
    private final Aspect.Builder aspect;
    private World world;

    protected AbstractSystem(Aspect.Builder aspect) {
        this.aspect = aspect;
    }

    protected void setEnabled(boolean enabled) {
        if (enabled) {
            getWorldManager().enableSystem(this);
        } else {
            getWorldManager().disableSystem(this);
        }
    }

    public void initialize() {
        // do nothing
    }

    public void begin() {
        // do nothing
    }

    public final void process(float delta) {
        processEntities(delta);
    }

    public void end() {
        // do nothing
    }

    public void dispose() {
        // do nothing
    }

    protected final void processEntities(float delta) {
        getWorldManager().entities(aspect)
            .stream()
            .forEach(entity -> processEntity(delta, entity));
    }

    protected abstract void processEntity(@Nonnegative float delta, int entity);

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
