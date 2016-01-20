package io.polymorphicpanda.ge0.ecs;

/**
 * @author Ranie Jade Ramiso
 */
public final class WorldConfig {

    private WorldConfig() {
    }

    public static class Builder {

        public WorldConfig build() {
            return new WorldConfig();
        }
    }
}
