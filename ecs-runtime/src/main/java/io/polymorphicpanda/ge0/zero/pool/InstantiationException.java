package io.polymorphicpanda.ge0.zero.pool;

import io.polymorphicpanda.ge0.ecs.component.Component;

/**
 * @author Ranie Jade Ramiso
 */
public class InstantiationException extends Exception {
    public InstantiationException(Class<? extends Component> component, Throwable throwable) {
        super(String.format("Failed to instantiate '%s'", component), throwable);
    }
}
