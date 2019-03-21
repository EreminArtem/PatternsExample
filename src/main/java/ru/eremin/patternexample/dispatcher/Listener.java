package ru.eremin.patternexample.dispatcher;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

public interface Listener<E> {

    void listen(E event);
}
