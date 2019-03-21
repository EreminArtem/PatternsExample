package ru.eremin.patternexample.model;

import ru.eremin.patternexample.annotation.Logger;
import ru.eremin.patternexample.annotation.Profiler;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public interface ICompany {
    @Logger
    @Profiler
    boolean interview(final Developer developer);

    @Logger
    @Profiler
    boolean checkProject(final Project project);

    @Logger
    @Profiler
    Developer getDeveloperForBackend(final Project project);

    @Logger
    @Profiler
    Developer getDeveloperForFrontend(final Project project);
}
