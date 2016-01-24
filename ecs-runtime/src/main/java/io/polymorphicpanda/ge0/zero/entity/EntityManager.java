package io.polymorphicpanda.ge0.zero.entity;

import io.polymorphicpanda.ge0.zero.util.identity.IdentityFactories;
import io.polymorphicpanda.ge0.zero.util.identity.RecyclingIdentityFactory;

/**
 * @author Ranie Jade Ramiso
 */
public class EntityManager {

    private final RecyclingIdentityFactory identityFactory = IdentityFactories.recycling();

    public void delete(int entityId) {
    }
}
