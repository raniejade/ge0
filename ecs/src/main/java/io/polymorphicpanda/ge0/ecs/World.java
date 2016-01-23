package io.polymorphicpanda.ge0.ecs;

import java.util.List;

import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.system.AbstractEntitySystem;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class World {

    public static World initialize(WorldConfig config) {
        return null;
    }

    public abstract List<Integer> entities(Aspect.Builder builder);
    public abstract void update(long delta);
    public abstract void enableSystem(AbstractEntitySystem system);
    public abstract void disableSystem(AbstractEntitySystem system);
    public abstract void dispose();
}
