package io.polymorphicpanda.ge0.zero.util.identity;

/**
 * @author Ranie Jade Ramiso
 */
public final class IdentityFactories {

    public static IdentityFactory basic() {
        return new BasicIdentityFactory();
    }

    public static RecyclingIdentityFactory recycling() {
        return new RecyclingIdentityFactory(basic());
    }

    private IdentityFactories() {
    }
}
