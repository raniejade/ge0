package io.polymorphicpanda.ge0.zero.component;

import java.util.BitSet;

/**
 * @author Ranie Jade Ramiso
 */
public class ComponentIdentity {
    private final int id;
    private final BitSet compositionBit;

    public ComponentIdentity(int id, BitSet compositionBit) {
        this.id = id;
        this.compositionBit = compositionBit;
    }

    public int getId() {
        return id;
    }

    public BitSet getCompositionBit() {
        return compositionBit;
    }
}
