package io.polymorphicpanda.panda.ecs.system;

import io.polymorphicpanda.panda.ecs.AbstractSystem;
import io.polymorphicpanda.panda.ecs.entity.Aspect;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractIntervalSystem extends AbstractSystem {
    private final float interval;
    private float accumulator;

    protected AbstractIntervalSystem(Aspect aspect, float interval) {
        super(aspect);
        this.interval = interval;
    }

    @Override
    public boolean canUpdate(float delta) {
        accumulator += delta;

        if (accumulator >= interval) {
            accumulator -= interval;
            return true;
        }
        return false;
    }
}
