package io.polymorphicpanda.ge0.zero.util.identity;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;

/**
 * @author Ranie Jade Ramiso
 */
public class RecyclingIdentityFactory extends ForwardingIdentityFactory {
    private final BitSet recycled = new BitSet();
    private final Deque<Integer> limbo = new ArrayDeque<>();

    public RecyclingIdentityFactory(IdentityFactory delegate) {
        super(delegate);
    }

    @Override
    public synchronized int generate() {
        if (!limbo.isEmpty()) {
            int id = limbo.pollFirst();
            recycled.set(id, false);
            return id;
        }
        return super.generate();
    }

    public synchronized void free(int id) {
        limbo.add(id);
        recycled.set(id);
    }
}
