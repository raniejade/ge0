package io.polymorphicpanda.ge0.zero.entity;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.ZeroRuntimeException;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.pool.InstantiationException;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityComponentManager {
    private static final BitSet ZERO = CompositionBits.compose(builder -> {});

    private final ComponentManager componentManager;

    // this two should always be in-synced
    // first one tracks an entities composition (what component it has)
    // while the second tracks entities with the same composition
    private final Map<Integer, BitSet> compositions = new HashMap<>();
    private final Map<BitSet, Set<Integer>> compositionIndex = new HashMap<>();

    private final Map<BitSet, Map<Integer, Component>> componentInstances = new HashMap<>();

    public EntityComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public void manage(int entityId) {
        manage(entityId, ZERO);
    }

    public void manage(int entityId, BitSet compositionBits) {
        assertNotManaged(entityId);
        compositions.put(entityId, compositionBits);
        addToIndex(entityId, compositionBits);
    }

    /**
     * Get entities with the given composition.
     */
    public Set<Integer> getEntities(BitSet composition) {
        return compositionIndex.get(composition);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T addComponent(int entityId, Class<T> component) {
        assertManaged(entityId);
        final BitSet bit = componentManager.compose(Arrays.asList(component));

        return (T) getInstances(bit).computeIfAbsent(entityId, key -> {
            try {

                final BitSet currentComposition = compositions.get(entityId);

                // update composition
                if (!currentComposition.intersects(bit)) {
                    final BitSet newComposition = CompositionBits.compose(builder -> {
                        builder.or(currentComposition);
                        builder.or(bit);
                    });

                    compositions.put(entityId, newComposition);
                    updateIndex(entityId, currentComposition, newComposition);
                }

                return componentManager.poolFor(component).request();
            } catch (InstantiationException e) {
                throw new ZeroRuntimeException(
                    String.format("Failed to create '%s' instance for entity '%d", component, entityId), e
                );
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> void removeComponent(int entityId, Class<T> component) {
        assertManaged(entityId);

        final BitSet bit = componentManager.compose(Arrays.asList(component));

        final Map<Integer, Component> instances = getInstances(bit);

        instances.computeIfPresent(entityId, (key, value) -> {
            final BitSet currentComposition = compositions.get(entityId);
            final BitSet newComposition = CompositionBits.compose(builder -> {
                builder.or(currentComposition);
                builder.xor(bit);
            });

            compositions.put(entityId, newComposition);
            updateIndex(entityId, currentComposition, newComposition);

            componentManager.poolFor(component).release((T) value);
            return null;
        });
    }

    public void release(int entityId) {
        assertManaged(entityId);
        removeFromIndex(entityId, compositions.remove(entityId));
    }

    private Map<Integer, Component> getInstances(BitSet componentBit) {
        return componentInstances.computeIfAbsent(componentBit, key -> new HashMap<>());
    }

    private void addToIndex(int entityId, BitSet composition) {
        compositionIndex.computeIfAbsent(composition, key -> new HashSet<>()).add(entityId);
    }

    private void removeFromIndex(int entityId, BitSet composition) {
        compositionIndex.remove(composition).remove(entityId);
    }

    private void updateIndex(int entityId, BitSet oldComposition, BitSet newComposition) {
        removeFromIndex(entityId, oldComposition);
        addToIndex(entityId, newComposition);
    }

    private void assertNotManaged(int entityId) {
        if (compositions.containsKey(entityId)) {
            throw new IllegalArgumentException(String.format("Entity '%d' is already managed", entityId));
        }
    }

    private void assertManaged(int entityId) {
        if (!compositions.containsKey(entityId)) {
            throw new IllegalArgumentException(String.format("Entity '%d' is not managed", entityId));
        }
    }
}
