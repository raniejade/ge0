package io.polymorphicpanda.ge0.zero.component;

import java.util.BitSet;
import java.util.List;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.ecs.component.ComponentMapper;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactories;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactory;

/**
 * @author Ranie Jade Ramiso
 */
public class ComponentManager {
    private final IdentityFactory identityFactory = IdentityFactories.basic();


    public BitSet compose(List<Class<? extends Component>> components) {
        return null;
    }

    public <T extends Component> ComponentMapper<T> mapper(Class<T> component) {
        return null;
    }
}
