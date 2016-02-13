package io.polymorphicpanda.panda.ecs.entity;

import io.polymorphicpanda.panda.ecs.util.collection.ImmutableIntBag;

/**
 * @author Ranie Jade Ramiso
 */
public interface EntitySubscription {
    ImmutableIntBag entities();
    void subscribe(EntitySubscriber subscriber);
    void unsubscribe(EntitySubscriber subscriber);
}
