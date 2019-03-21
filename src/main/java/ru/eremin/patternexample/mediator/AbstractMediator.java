package ru.eremin.patternexample.mediator;

import lombok.SneakyThrows;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.event.Event;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class AbstractMediator {

    private Dispatcher dispatcher;

    public AbstractMediator(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public <T extends Event> void registryAsync(final Class<T> eventSource, final Class<? extends Event> eventTarget) {
        dispatcher.subscribe(eventSource, new Listener<T>() {
            @Override
            @SneakyThrows
            public void listen(final T event) {
                final Event target = eventTarget.newInstance();
                target.setCompany(event.getCompany());
                target.setProject(event.getProject());
                dispatcher.asyncFire(target);
            }
        });
    }

    public <E> void subscribe(final Class<E> eventClass, final Listener<E> listener) {
        dispatcher.subscribe(eventClass, listener);
    }

    public <E> void unsubscribe(final Class<E> eventClass, final Listener<E> listener) {
        dispatcher.unsubscribe(eventClass, listener);
    }
}
