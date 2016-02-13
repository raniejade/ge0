package io.polymorphicpanda.panda.ecs.util.identity

import spock.lang.Specification

/**
 * @author Ranie Jade Ramiso
 */
abstract class BaseIdentityFactorySpec<T extends IdentityFactory> extends Specification {

    def T identityFactory

    def setup() {
        identityFactory = getIdentityFactory()
    }

    def "ids generated are unique"(int max) {
        setup:
            def tracker = new HashSet<Integer>()

        when:
            (1..max).each {
                tracker << it
            }

        then:
            tracker.size() == max

        where:
            max     | _
            1000    | _
            10000   | _
            100000  | _
    }

    abstract def T getIdentityFactory();
}
