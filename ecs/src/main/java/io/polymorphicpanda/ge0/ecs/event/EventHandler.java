package io.polymorphicpanda.ge0.ecs.event;

/**
 * @author Ranie Jade Ramiso
 */
public interface EventHandler<T extends Event> {
    void handle(T event, float delta);
}
