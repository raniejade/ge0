package io.polymorphicpanda.panda.ecs;

import io.polymorphicpanda.panda.ecs.entity.Aspect;
import io.polymorphicpanda.panda.ecs.entity.EntitySubscriber;
import io.polymorphicpanda.panda.ecs.entity.EntitySubscription;
import io.polymorphicpanda.panda.ecs.entity.EntitySubscriptionManager;
import io.polymorphicpanda.panda.ecs.util.collection.ImmutableIntBag;

/**
 * @author Ranie Jade Ramiso
 */
public abstract class AbstractSystem implements EntitySubscriber {
    private final EntitySubscription subscription;

    protected AbstractSystem(Aspect aspect) {
        subscription = peer.getEntitySubscriptionManager().subscription(aspect);
        subscription.subscribe(this);
    }

    public void initialize() {
        // do nothing
    }

    public void update(float delta) {
        subscription.entities()
            .forEach(entity -> process(delta, entity));
    }

    public void destroy() {
        // do nothing
    }

    public boolean canUpdate(float delta) {
        return true;
    }

    @Override
    public final void inserted(ImmutableIntBag entities) {
        entities.forEach(this::inserted);
    }

    @Override
    public final void removed(ImmutableIntBag entities) {
        entities.forEach(this::removed);
    }

    protected abstract void inserted(int entity);
    protected abstract void removed(int entity);
    protected abstract void process(float delta, int entity);


    // bridge between this system and the world
    static Peer peer;

    interface Peer {
        EntitySubscriptionManager getEntitySubscriptionManager();
    }
}
