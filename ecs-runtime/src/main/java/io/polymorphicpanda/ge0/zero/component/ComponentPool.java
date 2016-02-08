package io.polymorphicpanda.ge0.zero.component;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.pool.BasicLimbo;
import io.polymorphicpanda.ge0.zero.pool.InstantiationException;
import io.polymorphicpanda.ge0.zero.pool.Limbo;
import io.polymorphicpanda.ge0.zero.pool.Pool;

/**
 * @author Ranie Jade Ramiso
 */
class ComponentPool<T extends Component> extends Pool<T> {

    private final Class<T> component;

    public ComponentPool(Class<T> component) {
        this(component, new BasicLimbo<>());
    }

    public ComponentPool(Class<T> component, Limbo<T> limbo) {
        super(limbo);
        this.component = component;
    }


    @Override
    protected T create() throws InstantiationException {
        try {
            return component.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InstantiationException(component, e);
        }
    }
}
