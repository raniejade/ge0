package io.polymorphicpanda.panda.ecs.util.identity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ranie Jade Ramiso
 */
class BasicIdentityFactory implements IdentityFactory {
    private final AtomicInteger identityCount = new AtomicInteger();

    @Override
    public int generate() {
        return identityCount.getAndIncrement();
    }
}
