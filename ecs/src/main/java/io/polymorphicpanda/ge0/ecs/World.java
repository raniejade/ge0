package io.polymorphicpanda.ge0.ecs;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import io.polymorphicpanda.ge0.ecs.archetype.ArcheType;
import io.polymorphicpanda.ge0.ecs.component.Component;
import io.polymorphicpanda.ge0.ecs.component.ComponentMapper;
import io.polymorphicpanda.ge0.ecs.entity.Aspect;
import io.polymorphicpanda.ge0.ecs.event.EventBus;
import io.polymorphicpanda.ge0.ecs.system.AbstractSystem;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class World {
    private static final ServiceLoader<Runtime> SERVICE_LOADER = ServiceLoader.load(Runtime.class);

    public static World create(@Nonnull WorldConfig config) {
        final Runtime runtime = StreamSupport.stream(SERVICE_LOADER.spliterator(), false)
            .findAny()
            .get();
        return runtime.create(config);
    }

    public abstract void update(@Nonnegative float delta);
    public abstract void dispose();
    public abstract Manager getManager();

    public interface Runtime {
        World create(WorldConfig config);
    }

    public interface Manager {

        int create();
        void delete(int entityId);

        @Nonnull
        List<Integer> entities(@Nonnull Aspect.Builder builder);

        void enableSystem(@Nonnull AbstractSystem system);

        void disableSystem(@Nonnull AbstractSystem system);

        @Nonnull
        <T extends Component> ComponentMapper<T> mapper(@Nonnull Class<T> component);

        @Nonnull
        ArcheType.Builder archeType();

        @Nonnull
        ArcheType.Builder archeType(@Nonnull ArcheType base);


        @Nonnull
        EventBus eventBus(@Nonnull AbstractSystem entitySystem);
    }
}
