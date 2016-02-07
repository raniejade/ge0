package io.polymorphicpanda.ge0.zero.component

import io.polymorphicpanda.ge0.ecs.component.Component
import io.polymorphicpanda.ge0.zero.pool.AbstractPoolSpec
import io.polymorphicpanda.ge0.zero.pool.Pool

/**
 * @author Ranie Jade Ramiso
 */
class ComponentPoolSpec extends AbstractPoolSpec<TestComponent> {
    static class TestComponent implements Component {
        @Override
        void reset() {
        }
    }

    @Override
    protected Pool<TestComponent> getPool() {
        return new ComponentPool<TestComponent>(TestComponent)
    }
}
