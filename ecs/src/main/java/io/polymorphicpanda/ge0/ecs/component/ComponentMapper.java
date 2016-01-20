package io.polymorphicpanda.ge0.ecs.component;

/**
 * @author Ranie Jade Ramiso
 */
public interface ComponentMapper<T extends Component> {
    boolean contains(int entity);
    T get(int entity);
    void remove(int entity);
}
