package io.polymorphicpanda.ge0.zero.component;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactories;
import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactory;

/**
 * @author Ranie Jade Ramiso
 */
public class ComponentManager {
    private final IdentityFactory identityFactory = IdentityFactories.basic();
    private final Map<Class, ComponentIdentity> componentIdentities = new HashMap<>();

    public BitSet compose(List<Class<? extends Component>> components) {
        final List<ComponentIdentity> identities = components.stream()
            .map(this::getIdentity)
            .collect(Collectors.toList());


        return CompositionBits.compose(builder -> identities.stream()
            .forEach(identity -> builder.set(identity.getId())));
    }

    private ComponentIdentity getIdentity(Class<? extends Component> component) {
        return componentIdentities.computeIfAbsent(component, key -> {
            final int id = identityFactory.generate();
            final BitSet compositionBit = CompositionBits.compose(bitSet -> bitSet.set(id));

            return new ComponentIdentity(id, compositionBit);
        });
    }
}
