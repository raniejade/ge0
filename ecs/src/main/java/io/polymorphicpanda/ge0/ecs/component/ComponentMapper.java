package io.polymorphicpanda.ge0.ecs.component;

import javax.annotation.Nonnull;

/**
 * @author Ranie Jade Ramiso
 */
public interface ComponentMapper<T extends Component> {
    boolean contains(int entity);
    @Nonnull T create(int entity);
    void remove(int entity);
}
