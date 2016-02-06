package io.polymorphicpanda.ge0.zero.component;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.pool.Pool;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactories;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactory;

/**
 * @author Ranie Jade Ramiso
 */
public class ComponentManager {
    private final IdentityFactory identityFactory = IdentityFactories.basic();
    private final Map<Class, Integer> componentIdentities = new HashMap<>();
    private final Map<Integer, Pool> componentPool = new HashMap<>();

    public BitSet compose(List<Class<? extends Component>> components) {
        final List<Integer> identities = components.stream()
            .map(this::getIdentity)
            .collect(Collectors.toList());


        return CompositionBits.compose(builder -> identities.stream()
            .forEach(builder::set));
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> Pool<T> poolFor(Class<T> component) {
        return (Pool<T>) componentPool.computeIfAbsent(getIdentity(component), key -> new ComponentPool(component));
    }

    private int getIdentity(Class<? extends Component> component) {
        return componentIdentities.computeIfAbsent(component, key -> identityFactory.generate());
    }
}
