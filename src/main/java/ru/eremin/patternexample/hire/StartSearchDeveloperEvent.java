package ru.eremin.patternexample.hire;

import lombok.Getter;
import ru.eremin.patternexample.event.Event;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

@Getter
public class StartSearchDeveloperEvent extends Event {

    public StartSearchDeveloperEvent(final Company company, final Project project) {
        super(company, project);
    }
}
