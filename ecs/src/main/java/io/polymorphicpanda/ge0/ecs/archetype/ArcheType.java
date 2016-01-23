package io.polymorphicpanda.ge0.ecs.archetype;

import io.polymorphicpanda.ge0.ecs.component.Component;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class ArcheType {

    public abstract int create();

    public interface Builder {
        Builder with(Class<? extends Component> component);
        ArcheType build();
    }
}
