package ru.eremin.patternexample.check_project;

import lombok.Getter;
import ru.eremin.patternexample.event.Event;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

@Getter
public class StartCheckProjectsEvent extends Event {

    public StartCheckProjectsEvent(final Company company, final Project project) {
        super(company, project);
    }
}
