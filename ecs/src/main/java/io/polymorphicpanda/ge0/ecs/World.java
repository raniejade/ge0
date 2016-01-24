package io.polymorphicpanda.ge0.ecs;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import java.util.List;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.ecs.component.ComponentMapper;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.event.EventBus;
import io.polymorphicpanda.ge0.ecs.system.AbstractEntitySystem;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class World {

    public static World initialize(@Nonnull WorldConfig config) {
        return null;
    }

    public abstract void update(@Nonnegative float delta);
    public abstract void dispose();
    public abstract Manager getManager();


    public interface Manager {

        int create();
        void delete(int entityId);

        @Nonnull
        List<Integer> entities(@Nonnull Aspect.Builder builder);

        void enableSystem(@Nonnull AbstractEntitySystem system);

        void disableSystem(@Nonnull AbstractEntitySystem system);

        @Nonnull
        <T extends Component> ComponentMapper<T> mapper(@Nonnull Class<T> component);

        @Nonnull
        ArcheType.Builder archeType();

        @Nonnull
        ArcheType.Builder archeType(@Nonnull ArcheType base);


        @Nonnull
        EventBus eventBus(@Nonnull AbstractEntitySystem entitySystem);
    }
}
