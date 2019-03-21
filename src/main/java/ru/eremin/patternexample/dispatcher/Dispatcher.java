package ru.eremin.patternexample.dispatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class Dispatcher {

    private final Map<Class, List<Listener>> listeners = new HashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final Dispatcher INSTANCE = new Dispatcher();

    public static Dispatcher getInstance(){return INSTANCE;}

    private Dispatcher(){}

    public synchronized <E> void subscribe(final Class<E> eventClass, final Listener<E> listener) {
        if (eventClass == null || listener == null) return;
        getListeners(eventClass).add(listener);
    }

    public synchronized <E> void unsubscribe(final Class<E> eventClass, final Listener<E> listener) {
        if (eventClass == null || listener == null) return;
        getListeners(eventClass).remove(listener);
    }

    public synchronized void fire(final Object event) {
        if (event == null) return;
        final List<Listener> list = listeners.get(event.getClass());
        if (list == null) return;
        for (final Listener listener : list) {
            listener.listen(event);
        }
    }

    public synchronized void asyncFire(final Object event){
        if (event == null) return;
        final List<Listener> list = listeners.get(event.getClass());
        if (list == null) return;
        for (final Listener listener : list) {
            executorService.submit(() -> listener.listen(event));
        }
    }

    public void shutdown(){
        executorService.shutdown();
    }

    private List<Listener> getListeners(final Class eventClass) {
        if (eventClass == null) return null;
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>());
        return listeners.get(eventClass);
    }

}
