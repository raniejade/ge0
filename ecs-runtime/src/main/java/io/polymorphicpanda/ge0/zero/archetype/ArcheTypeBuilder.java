package io.polymorphicpanda.ge0.zero.archetype;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.entity.EntityManager;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;

/**
 * @author Ranie Jade Ramiso
 */
public class ArcheTypeBuilder implements ArcheType.Builder {

    private final ArcheTypeImpl base;
    private final EntityManager entityManager;
    private final ComponentManager componentManager;

    private final Set<Class<? extends Component>> components = new HashSet<>();

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
        BitSet composition = componentManager.compose(components);

        if (base != null) {
            composition = CompositionBits.compose(builder -> builder.or(base.getComposition()));
        }

        return new ArcheTypeImpl(entityManager, composition);
    }
}
