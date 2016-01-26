package io.polymorphicpanda.ge0.zero.util.identity

/**
 * @author Ranie Jade Ramiso
 */
class RecyclingIdentityFactorySpec extends BaseIdentityFactorySpec<RecyclingIdentityFactory> {


    def "should reuse freed up ids"(int max) {
        setup:
            def tracker = new HashSet<Integer>()

            // generate the ids
            (1..max).each {
                tracker << it
            }

            // free everything
            tracker.each {
                getIdentityFactory().free(it)
            }


            // clear the tracker
            tracker.clear()


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

    @Override
    RecyclingIdentityFactory getIdentityFactory() {
        return IdentityFactories.recycling()
    }
}
