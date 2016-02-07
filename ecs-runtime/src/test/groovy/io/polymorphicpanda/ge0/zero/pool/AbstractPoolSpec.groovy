package io.polymorphicpanda.ge0.zero.pool

import io.polymorphicpanda.ge0.ecs.pool.Poolable
import spock.lang.Specification

/**
 * @author Ranie Jade Ramiso
 */
abstract class AbstractPoolSpec<T extends Poolable> extends Specification {
    protected abstract Pool<T> getPool();

    private Pool<T> pool;

    def setup() {
        pool = getPool()
    }


    def "reuse instances on release"() {
        setup:
            def first = pool.request()

        when:
            pool.release(first)

        then:
            pool.request().is(first)
    }

    def "create new instances if none is available"() {
        setup:
            def first = pool.request()

        expect:
            !first.is(pool.request())
    }
}
