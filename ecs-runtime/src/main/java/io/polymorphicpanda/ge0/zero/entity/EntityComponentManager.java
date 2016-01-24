package io.polymorphicpanda.ge0.zero.entity;

import java.util.BitSet;

import io.polymorphicpanda.ge0.zero.component.ComponentManager;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityComponentManager {
    private final ComponentManager componentManager;

    public EntityComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public void manage(int entityId) {

    }

    public void manage(int entityId, BitSet compositionBits) {

    }

    public void release(int entityId) {

    }
}
