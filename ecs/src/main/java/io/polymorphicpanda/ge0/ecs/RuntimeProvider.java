package io.polymorphicpanda.ge0.ecs;

/**
 * @author Ranie Jade Ramiso
 */
public final class RuntimeProvider {
    public interface Runtime {
        World create(WorldConfig config);
    }

    private static Runtime runtime;

    public static World initialize(WorldConfig config) {
        if (runtime == null) {
            // TODO: load runtime here
        }
        return runtime.create(config);
    }
}
