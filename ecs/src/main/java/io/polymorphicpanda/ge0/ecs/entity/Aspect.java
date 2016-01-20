package io.polymorphicpanda.ge0.ecs.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.polymorphicpanda.ge0.ecs.component.Component;

/**
 * @author Ranie Jade Ramiso
 */
public final class Aspect {

    private Aspect() {
    }

    public static Builder all(List<Class<? extends Component>> components) {
        return new Builder().all(components);
    }

    public static Builder all(Class<? extends Component>... components) {
        return new Builder().all(Arrays.asList(components));
    }

    public static Builder exclude(List<Class<? extends Component>> components) {
        return new Builder().exclude(components);
    }

    public static Builder exclude(Class<? extends Component>... components) {
        return new Builder().exclude(Arrays.asList(components));
    }

    public static Builder any(List<Class<? extends Component>> components) {
        return new Builder().any(components);
    }

    public static Builder any(Class<? extends Component>... components) {
        return new Builder().any(Arrays.asList(components));
    }

    public static final class Builder {
        private final Set<Class<? extends Component>> all = new HashSet<>();
        private final Set<Class<? extends Component>> exclude = new HashSet<>();
        private final Set<Class<? extends Component>> any = new HashSet<>();

        private Builder() {}

        public Builder all(List<Class<? extends Component>> components) {
            all.addAll(components);
            return this;
        }

        public Builder all(Class<? extends Component>... components) {
            return all(Arrays.asList(components));
        }

        public Builder exclude(List<Class<? extends Component>> components) {
            exclude.addAll(components);
            return this;
        }

        public Builder exclude(Class<? extends Component>... components) {
            return exclude(Arrays.asList(components));
        }

        public Builder any(List<Class<? extends Component>> components) {
            any.addAll(components);
            return this;
        }

        public Builder any(Class<? extends Component>... components) {
            return any(Arrays.asList(components));
        }

        public Set<Class<? extends Component>> getAll() {
            return all;
        }

        public Set<Class<? extends Component>> getExclude() {
            return exclude;
        }

        public Set<Class<? extends Component>> getAny() {
            return any;
        }
    }

}
