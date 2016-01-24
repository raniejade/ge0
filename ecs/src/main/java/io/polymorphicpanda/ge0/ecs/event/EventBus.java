package io.polymorphicpanda.ge0.ecs.event;

import javax.annotation.Nonnull;

import java.util.List;

/**
 * @author Ranie Jade Ramiso
 */
public interface EventBus {
    void publish(@Nonnull Event event);
    List<Event> pollEvents();
    <T extends Event> void subscribe(@Nonnull Class<T> type);
    <T extends Event> void unsubscribe(@Nonnull Class<T> type);
}
