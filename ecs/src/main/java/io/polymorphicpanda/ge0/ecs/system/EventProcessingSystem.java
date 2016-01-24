package io.polymorphicpanda.ge0.ecs.system;

import javax.annotation.Nonnegative;

import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.event.Event;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class EventProcessingSystem extends AbstractSystem {
    protected EventProcessingSystem(Aspect.Builder aspect) {
        super(aspect);
    }

    @Override
    public void begin() {
        super.begin();
        getEventBus().pollEvents()
            .stream()
            .forEach(this::processEvent);
    }

    protected abstract void processEvent(Event event);

    @Override
    protected void processEntity(@Nonnegative float delta, int entity) {
        // do nothing
    }
}
