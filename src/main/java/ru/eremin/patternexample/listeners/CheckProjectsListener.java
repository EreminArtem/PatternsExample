package ru.eremin.patternexample.listeners;

import lombok.SneakyThrows;
import ru.eremin.patternexample.backend_process.DesignBackendEvent;
import ru.eremin.patternexample.check_project.CheckProjectsEvent;
import ru.eremin.patternexample.check_project.StartCheckProjectsEvent;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.frontend_process.DesignFrontendEvent;
import ru.eremin.patternexample.model.Project;
import ru.eremin.patternexample.process.CheckFinishProjectEvent;

import java.util.List;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class CheckProjectsListener implements Listener<CheckProjectsEvent> {

    @Override
    @SneakyThrows
    public void listen(final CheckProjectsEvent event) {
        Dispatcher.getInstance().asyncFire(new StartCheckProjectsEvent(event.getCompany(), event.getProject()));
        System.out.println("check project");
        Thread.sleep(100);
        List<Project> projects = event.getCompany().getProjects();
        if(projects == null) Dispatcher.getInstance().asyncFire(new SearchProjectListener());
        for (final Project project : projects) {
            if (!project.isBackendFinish()) Dispatcher.getInstance().asyncFire(new DesignBackendEvent(event.getCompany(), project));
            else if (!project.isFrontendFinish()) Dispatcher.getInstance().asyncFire(new DesignFrontendEvent(event.getCompany(), project));
            else Dispatcher.getInstance().asyncFire(new CheckFinishProjectEvent(event.getCompany(), project));
        }
    }
}
