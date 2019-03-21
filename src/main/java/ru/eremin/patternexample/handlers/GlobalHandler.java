package ru.eremin.patternexample.handlers;

import ru.eremin.patternexample.annotation.Logger;
import ru.eremin.patternexample.annotation.Profiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class GlobalHandler implements InvocationHandler {

    private Object target;

    private LoggerHandler loggerHandler;

    private ProfilerHandler profilerHandler;

    public GlobalHandler(final Object target) {
        this.target = target;
        this.loggerHandler = new LoggerHandler(target);
        this.profilerHandler = new ProfilerHandler(target);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Logger.class)) {
            return loggerHandler.invoke(target, method, args);
        }
        if (method.isAnnotationPresent(Profiler.class)) {
            return profilerHandler.invoke(target, method, args);
        }
        if (target == null) return null;
        return method.invoke(target, args);
    }
}
