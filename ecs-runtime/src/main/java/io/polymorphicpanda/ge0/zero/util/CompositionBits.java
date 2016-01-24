package io.polymorphicpanda.ge0.zero.util;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Ranie Jade Ramiso
 */
public final class CompositionBits {
    private static final Map<BitSet, BitSet> CACHE = new HashMap<>();
    private static final BitSet BUILDER = new BitSet();

    public static BitSet compose(Consumer<BitSet> consumer) {
        BUILDER.clear();
        consumer.accept(BUILDER);

        if (CACHE.containsKey(BUILDER)) {
            return CACHE.get(BUILDER);
        }

        final BitSet clone = ((BitSet) BUILDER.clone());

        CACHE.put(clone, clone);

        return clone;
    }

    private CompositionBits() {
    }
}
