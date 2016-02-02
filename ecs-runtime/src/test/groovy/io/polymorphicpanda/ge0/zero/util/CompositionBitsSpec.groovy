package io.polymorphicpanda.ge0.zero.util

import spock.lang.Specification
import spock.lang.Unroll

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


    @Unroll
    def "returned instances are immutable (#method, #args)"(method, args) {
        setup:
            def composition = CompositionBits.compose({builder -> })


        when:
            if (args != null) {
                composition."$method"(args)
            } else {
                composition."$method"()
            }

        then:
            thrown(UnsupportedOperationException)


        where:
            method        | args
            "flip"        | [1]
            "flip"        | [0, 3]
            "set"         | [2]
            "set"         | [4, true]
            "set"         | [0, 5]
            "set"         | [1, 6, false]
            "clear"       | [5]
            "clear"       | [5, 9]
            "clear"       | null
            "and"         | [new BitSet()]
            "or"          | [new BitSet()]
            "xor"         | [new BitSet()]
            "andNot"      | [new BitSet()]
            "clone"       | null
    }
}
