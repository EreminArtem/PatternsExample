package ru.eremin.patternexample.listeners;

import lombok.SneakyThrows;
import ru.eremin.patternexample.backend_process.DesignBackendEvent;
import ru.eremin.patternexample.backend_process.FinishDesignBackendEvent;
import ru.eremin.patternexample.backend_process.StartDesignBackendEvent;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.hire.SearchDeveloperEvent;
import ru.eremin.patternexample.model.Developer;


/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class DesignBackendListener implements Listener<DesignBackendEvent> {

    @Override
    @SneakyThrows
    public void listen(final DesignBackendEvent event) {
        Dispatcher.getInstance().asyncFire(new StartDesignBackendEvent(event.getCompany(), event.getProject()));
        final Developer developer = event.getProject().getPerformer().getDeveloperForBackend(event.getProject());
        if(developer == null) {
            Dispatcher.getInstance().asyncFire(new SearchDeveloperEvent(event.getProject().getPerformer(), event.getProject()));
            return;
        }
        System.out.println("Start Backend Design");
        Thread.sleep(2000);
        event.getProject().setBackendFinish(true);
        Dispatcher.getInstance().asyncFire(new FinishDesignBackendEvent(event.getCompany(), event.getProject()));
    }
}
