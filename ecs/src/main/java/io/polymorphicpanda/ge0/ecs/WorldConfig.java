package io.polymorphicpanda.ge0.ecs;

import java.util.LinkedList;
import java.util.List;

import io.polymorphicpanda.ge0.ecs.system.AbstractSystem;

/**
 * @author Ranie Jade Ramiso
 */
public final class WorldConfig {

    private final List<AbstractSystem> systems;
    private WorldConfig(List<AbstractSystem> systems) {
        this.systems = systems;
    }

    public static class Builder {
        private final List<AbstractSystem> systems = new LinkedList<>();

        public Builder addSystem(AbstractSystem system) {
            systems.add(system);
            return this;
        }

        public WorldConfig build() {
            return new WorldConfig(systems);
        }
    }
}
