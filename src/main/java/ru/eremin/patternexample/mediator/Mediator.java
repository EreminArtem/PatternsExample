package ru.eremin.patternexample.mediator;

import ru.eremin.patternexample.backend_process.DesignBackendEvent;
import ru.eremin.patternexample.backend_process.FinishDesignBackendEvent;
import ru.eremin.patternexample.check_project.CheckProjectsEvent;
import ru.eremin.patternexample.check_project.FinishCheckProjectsEvent;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.frontend_process.DesignFrontendEvent;
import ru.eremin.patternexample.frontend_process.FinishDesignFrontendEvent;
import ru.eremin.patternexample.hire.FinishSearchDeveloperEvent;
import ru.eremin.patternexample.hire.SearchDeveloperEvent;
import ru.eremin.patternexample.listeners.CheckProjectsListener;
import ru.eremin.patternexample.listeners.DesignBackendListener;
import ru.eremin.patternexample.listeners.DesignFrontendListener;
import ru.eremin.patternexample.listeners.SearchDeveloperListener;
import ru.eremin.patternexample.listeners.SearchProjectListener;
import ru.eremin.patternexample.process.CheckFinishProjectEvent;
import ru.eremin.patternexample.search_project.FinishSearchProjectEvent;
import ru.eremin.patternexample.search_project.SearchProjectEvent;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class Mediator extends AbstractMediator{

    public Mediator(final Dispatcher dispatcher) {
        super(dispatcher);

        subscribe(CheckProjectsEvent.class, new CheckProjectsListener());
        subscribe(DesignBackendEvent.class, new DesignBackendListener());
        subscribe(DesignFrontendEvent.class, new DesignFrontendListener());
        subscribe(SearchDeveloperEvent.class, new SearchDeveloperListener());
        subscribe(SearchProjectEvent.class, new SearchProjectListener());

        registryAsync(FinishSearchProjectEvent.class, CheckProjectsEvent.class);
        registryAsync(FinishDesignBackendEvent.class, CheckProjectsEvent.class);
        registryAsync(FinishDesignFrontendEvent.class, CheckProjectsEvent.class);
        registryAsync(FinishSearchDeveloperEvent.class, CheckProjectsEvent.class);
        registryAsync(FinishCheckProjectsEvent.class, CheckFinishProjectEvent.class);
    }
}
