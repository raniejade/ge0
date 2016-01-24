package io.polymorphicpanda.ge0.zero.entity;

import java.util.BitSet;

import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactories;
import io.polymorphicpanda.ge0.zero.util.identity.RecyclingIdentityFactory;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityManager {
    private final RecyclingIdentityFactory identityFactory = IdentityFactories.recycling();
    private final EntityComponentManager entityComponentManager;

    public EntityManager(EntityComponentManager entityComponentManager) {
        this.entityComponentManager = entityComponentManager;
    }

    public int create() {
        return create(null);
    }

    public int create(BitSet compositionBits) {
        final int entityId = identityFactory.generate();

        if (compositionBits == null) {
            entityComponentManager.manage(entityId);
        } else {
            entityComponentManager.manage(entityId, compositionBits);
        }

        return entityId;
    }

    public void delete(int entityId) {
        identityFactory.free(entityId);
        entityComponentManager.release(entityId);
    }
}
