package io.polymorphicpanda.ge0.ecs.event;

import javax.annotation.Nonnull;

/**
 * @author Ranie Jade Ramiso
 */
public interface EventBus {
    void publish(@Nonnull Event event);
    <T extends Event> void subscribe(@Nonnull Class<T> type, @Nonnull EventHandler<T> handler);
    void unsubscribe(@Nonnull EventHandler<?> handler);
}
