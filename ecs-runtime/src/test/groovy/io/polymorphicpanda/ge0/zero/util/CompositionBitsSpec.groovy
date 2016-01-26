package io.polymorphicpanda.ge0.zero.util

import spock.lang.Specification

/**
 * @author Ranie Jade Ramiso
 */
class CompositionBitsSpec extends Specification {

    def "same composition yields the same instance"(int bit) {
        setup:
            def first = CompositionBits.compose({builder ->
                builder.set(bit)
            })

        when:
            def second = CompositionBits.compose({builder ->
                builder.set(bit)
            })

        then:
            first.is(second)

        where:
            bit | _
            1   | _
            2   | _
            3   | _
            4   | _
    }
}
