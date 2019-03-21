package ru.eremin.patternexample.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class ProfilerHandler implements InvocationHandler {

    private Object target;

    public ProfilerHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final long start = System.nanoTime();
        final Object result = method.invoke(target, args);
        final long finish = System.nanoTime();
        final long delta = finish-start;
        System.out.println(target.getClass().getSimpleName() + method.getName()+ delta +"ms");
        return result;
    }
}
