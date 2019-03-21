package ru.eremin.patternexample.listeners;

import lombok.SneakyThrows;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.frontend_process.DesignFrontendEvent;
import ru.eremin.patternexample.frontend_process.FinishDesignFrontendEvent;
import ru.eremin.patternexample.frontend_process.StartDesignFrontendEvent;
import ru.eremin.patternexample.hire.SearchDeveloperEvent;
import ru.eremin.patternexample.model.Developer;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class DesignFrontendListener implements Listener<DesignFrontendEvent> {

    @Override
    @SneakyThrows
    public void listen(final DesignFrontendEvent event) {
        Dispatcher.getInstance().asyncFire(new StartDesignFrontendEvent(event.getCompany(), event.getProject()));
        final Developer developer = event.getProject().getPerformer().getDeveloperForFrontend(event.getProject());
        if(developer == null) {
            Dispatcher.getInstance().asyncFire(new SearchDeveloperEvent(event.getProject().getPerformer(), event.getProject()));
            return;
        }
        System.out.println("Start Frontend Design");
        Thread.sleep(2000);
        event.getProject().setFrontendFinish(true);
        Dispatcher.getInstance().asyncFire(new FinishDesignFrontendEvent(event.getCompany(), event.getProject()));
    }
}
