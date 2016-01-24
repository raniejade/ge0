package io.polymorphicpanda.ge0.zero.util.identity;

/**
 * @author Ranie Jade Ramiso
 */
public class ForwardingIdentityFactory implements IdentityFactory {
    private final IdentityFactory delegate;

    public ForwardingIdentityFactory(IdentityFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public int generate() {
        return delegate.generate();
    }
}
