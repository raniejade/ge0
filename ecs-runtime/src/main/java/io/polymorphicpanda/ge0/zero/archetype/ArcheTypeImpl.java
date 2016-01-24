package io.polymorphicpanda.ge0.zero.archetype;

import java.util.BitSet;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.zero.entity.EntityManager;

/**
 * @author Ranie Jade Ramiso
 */
class ArcheTypeImpl extends ArcheType {

    private final EntityManager entityManager;
    private final BitSet composition;

    public ArcheTypeImpl(EntityManager entityManager, BitSet composition) {
        this.entityManager = entityManager;
        this.composition = composition;
    }

    @Override
    public int create() {
        return 0;
    }

    public BitSet getComposition() {
        return composition;
    }
}
