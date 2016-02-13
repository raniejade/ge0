package io.polymorphicpanda.panda.ecs.entity;

import io.polymorphicpanda.panda.ecs.util.collection.ImmutableIntBag;

/**
 * @author Ranie Jade Ramiso
 */
public interface EntitySubscriber {
    void inserted(ImmutableIntBag entities);
    void removed(ImmutableIntBag entities);
}
