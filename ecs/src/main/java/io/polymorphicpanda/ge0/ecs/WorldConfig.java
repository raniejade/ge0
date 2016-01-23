package io.polymorphicpanda.ge0.ecs;

import java.util.LinkedList;
import java.util.List;

import io.polymorphicpanda.ge0.ecs.system.AbstractEntitySystem;

/**
 * @author Ranie Jade Ramiso
 */
public final class WorldConfig {

    private final List<AbstractEntitySystem> systems;
    private WorldConfig(List<AbstractEntitySystem> systems) {
        this.systems = systems;
    }

    public static class Builder {
        private final List<AbstractEntitySystem> systems = new LinkedList<>();

        public Builder addSystem(AbstractEntitySystem system) {
            systems.add(system);
            return this;
        }

        public WorldConfig build() {
            return new WorldConfig(systems);
        }
    }
}
