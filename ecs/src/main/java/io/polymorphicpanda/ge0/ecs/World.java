package io.polymorphicpanda.ge0.ecs;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import java.util.List;

import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.system.AbstractEntitySystem;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class World {

    public static World initialize(@Nonnull WorldConfig config) {
        return null;
    }

    public abstract @Nonnull List<Integer> entities(@Nonnull Aspect.Builder builder);
    public abstract void update(@Nonnegative float delta);
    public abstract void enableSystem(@Nonnull AbstractEntitySystem system);
    public abstract void disableSystem(@Nonnull AbstractEntitySystem system);
    public abstract void dispose();
}
