package ru.eremin.patternexample.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class LoggerHandler implements InvocationHandler {

    private Object target;

    public LoggerHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println(target.getClass() + method.getName());
        return method.invoke(target, args);
    }
}
