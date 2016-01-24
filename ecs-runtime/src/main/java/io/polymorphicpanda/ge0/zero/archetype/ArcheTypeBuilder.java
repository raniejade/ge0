package io.polymorphicpanda.ge0.zero.archetype;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.entity.EntityManager;

/**
 * @author Ranie Jade Ramiso
 */
public class ArcheTypeBuilder implements ArcheType.Builder {

    private final ArcheTypeImpl base;
    private final EntityManager entityManager;
    private final ComponentManager componentManager;

    private final List<Class<? extends Component>> components = new ArrayList<>();

    public ArcheTypeBuilder(ArcheTypeImpl base, EntityManager entityManager, ComponentManager componentManager) {
        this.base = base;
        this.entityManager = entityManager;
        this.componentManager = componentManager;
    }

    @Override
    public ArcheType.Builder with(Class<? extends Component> component) {
        components.add(component);
        return this;
    }

    @Override
    public ArcheType build() {
        final BitSet composition = componentManager.compose(components);

        if (base != null) {
            composition.or(base.getComposition());
        }

        return new ArcheTypeImpl(entityManager, composition);
    }
}
