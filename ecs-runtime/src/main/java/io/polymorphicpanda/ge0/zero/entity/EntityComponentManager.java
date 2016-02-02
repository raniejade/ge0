package io.polymorphicpanda.ge0.zero.entity;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.zero.component.ComponentIdentity;
import io.polymorphicpanda.ge0.zero.component.ComponentManager;
import io.polymorphicpanda.ge0.zero.util.CompositionBits;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityComponentManager {
    private static final BitSet ZERO = CompositionBits.compose(builder -> {});

    private final ComponentManager componentManager;
    private final Map<Integer, BitSet> compositions = new HashMap<>();


    public EntityComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public void manage(int entityId) {
        manage(entityId, ZERO);
    }

    public void manage(int entityId, BitSet compositionBits) {
        checkIfManaged(entityId);
        compositions.put(entityId, compositionBits);
    }

    public void addComponent(int entityId, Class<? extends Component> component) {
        checkIfManaged(entityId);
    }

    public void removeComponent(int entityId, Class<? extends Component> component) {
        checkIfManaged(entityId);
    }

    public void release(int entityId) {
        if (!compositions.containsKey(entityId)) {
            throw new IllegalArgumentException(String.format("Entity '%d' is not managed", entityId));
        }
        compositions.remove(entityId);
    }

    private void checkIfManaged(int entityId) {
        if (compositions.containsKey(entityId)) {
            throw new IllegalArgumentException(String.format("Entity '%d' is already managed", entityId));
        }
    }
}
