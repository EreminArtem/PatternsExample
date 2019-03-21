package ru.eremin.patternexample.listeners;

import lombok.SneakyThrows;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.model.Project;
import ru.eremin.patternexample.model.ProjectExchange;
import ru.eremin.patternexample.search_project.FinishSearchProjectEvent;
import ru.eremin.patternexample.search_project.SearchProjectEvent;
import ru.eremin.patternexample.search_project.StartSearchProjectEvent;

import java.util.List;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class SearchProjectListener implements Listener<SearchProjectEvent> {

    @Override
    @SneakyThrows
    public void listen(final SearchProjectEvent event) {
        Dispatcher.getInstance().asyncFire(new StartSearchProjectEvent(event.getCompany(), event.getProject()));
        System.out.println("start search project");
        Thread.sleep(100);
        final List<Project> projects = ProjectExchange.getInstance().getProjects();
        if(projects == null) return;
        for (final Project project : projects) {
            if(event.getCompany().checkProject(project)) {
                event.getCompany().addProject(project);
                project.setPerformer(event.getCompany());
                Dispatcher.getInstance().asyncFire(new FinishSearchProjectEvent(event.getCompany(), project));
            }
        }
    }
}
