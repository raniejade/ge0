package io.polymorphicpanda.ge0.zero.component

import io.polymorphicpanda.ge0.ecs.component.Component
import spock.lang.Specification

/**
 * @author Ranie Jade Ramiso
 */
class ComponentManagerSpec extends Specification {

    static class TestComponent1 implements Component {
        @Override
        void reset() {
        }
    }

    static class TestComponent2 implements Component {
        @Override
        void reset() {
        }
    }

    static class TestComponent3 implements Component {
        @Override
        void reset() {
        }
    }

    def componentManager = new ComponentManager()


    def "compose is idempotent "(Set<Class> test) {

        expect:
            componentManager.compose(test) == componentManager.compose(test)

        where:
            test                                             | _
            [TestComponent1, TestComponent2, TestComponent3] | _
            [TestComponent1, TestComponent3]                 | _
            [TestComponent1, TestComponent2]                 | _
            [TestComponent2, TestComponent3]                 | _
            [TestComponent1]                                 | _
            [TestComponent2]                                 | _
            [TestComponent3]                                 | _
    }

    def "poolFor will always return the same instance"(Class component) {

        expect:
            componentManager.poolFor(component).is(componentManager.poolFor(component))

        where:
            component      | _
            TestComponent1 | _
            TestComponent2 | _
            TestComponent3 | _
    }
}
