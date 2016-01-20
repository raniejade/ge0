package io.polymorphicpanda.ge0.ecs.entity;

import java.util.Arrays;
import java.util.List;

import io.polymorphicpanda.ge0.ecs.component.Component;

/**
 * @author Ranie Jade Ramiso
 */
public class Family {

    public static Builder all(List<Class<? extends Component>> components) {
        return null;
    }

    public static Builder all(Class<? extends Component>... components) {
        return all(Arrays.asList(components));
    }

    public static Builder exclude(List<Class<? extends Component>> components) {
        return null;
    }

    public static Builder exclude(Class<? extends Component>... components) {
        return exclude(Arrays.asList(components));
    }

    public static Builder any(List<Class<? extends Component>> components) {
        return null;
    }

    public static Builder any(Class<? extends Component>... components) {
        return any(Arrays.asList(components));
    }

    public interface Builder {
        List<Integer> entities();
    }
}
