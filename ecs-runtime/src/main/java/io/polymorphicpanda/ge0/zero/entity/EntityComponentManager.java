package io.polymorphicpanda.ge0.zero.entity;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityComponentManager {
    private static final BitSet ZERO = CompositionBits.compose(builder -> {});

    private final ComponentManager componentManager;
    private final Map<Integer, BitSet> compositions = new HashMap<>();
    private final Map<BitSet, Map<Integer, ? extends Component>> componentInstances = new HashMap<>();


    public EntityComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public void manage(int entityId) {
        manage(entityId, ZERO);
    }

    public void manage(int entityId, BitSet compositionBits) {
        assertNotManaged(entityId);
        compositions.put(entityId, compositionBits);
    }

    public <T extends Component> T addComponent(int entityId, Class<T> component) {
        assertManaged(entityId);
        return null;
    }

    public void removeComponent(int entityId, Class<? extends Component> component) {
        assertManaged(entityId);
    }

    public void release(int entityId) {
        assertManaged(entityId);
        compositions.remove(entityId);
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
