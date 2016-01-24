package io.polymorphicpanda.ge0.zero.archetype;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.entity.EntityManager;

/**
 * @author Ranie Jade Ramiso
 */
public class ArcheTypeManager {

    private final EntityManager entityManager;
    private final ComponentManager componentManager;

    public ArcheTypeManager(EntityManager entityManager, ComponentManager componentManager) {
        this.entityManager = entityManager;
        this.componentManager = componentManager;
    }

    public ArcheType.Builder builder() {
        return builder(null);
    }

    public ArcheType.Builder builder(ArcheType base) {
        return new ArcheTypeBuilder((ArcheTypeImpl) base, entityManager, componentManager);
    }
}
