package io.polymorphicpanda.ge0.zero.pool

import io.polymorphicpanda.ge0.ecs.pool.Poolable
import spock.lang.Specification

/**
 * @author Ranie Jade Ramiso
 */
class SizeConstrainedLimboSpec extends Specification {

    class TestPoolable implements Poolable {
        @Override
        void reset() {
        }
    }

    def "shrink when size constraint is reached on push"(int minimum, int limit) {
        setup:
            def limbo = new SizeConstrainedLimbo<TestPoolable>(new BasicLimbo<TestPoolable>(), minimum, limit)

            (0..limit).each {
                limbo.push(new TestPoolable())
            }

        when:
            limbo.push(new TestPoolable())

        then:
            limbo.size == minimum + 1

        where:
            minimum | limit
            0       | 3
            3       | 10
            5       | 6
    }

    def "shrink when size constraint is reached on poll"(int minimum, int limit, boolean isNull, int size) {
        setup:
            def limbo = new SizeConstrainedLimbo<TestPoolable>(new BasicLimbo<TestPoolable>(), minimum, limit)

            (0..limit).each {
                limbo.push(new TestPoolable())
            }

        when:
            def res = limbo.poll()

        then:
            (res == null) == isNull
            limbo.size == size

        where:
            minimum | limit | isNull    | size
            0       | 3     | true      | 0
            3       | 10    | false     | 2
            5       | 6     | false     | 4
    }

    def "shrink when size constraint is reached on empty check"(int minimum, int limit) {
        setup:
            def limbo = new SizeConstrainedLimbo<TestPoolable>(new BasicLimbo<TestPoolable>(), minimum, limit)

            (0..limit).each {
                limbo.push(new TestPoolable())
            }

        when:
            limbo.isEmpty()

        then:
            limbo.size == minimum

        where:
            minimum | limit
            0       | 3
            3       | 10
            5       | 6
    }
}
