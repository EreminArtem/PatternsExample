package ru.eremin.patternexample.process;

import lombok.Getter;
import ru.eremin.patternexample.event.Event;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

@Getter
public class StartProjectEvent extends Event {

    public StartProjectEvent(final Company company, final Project project) {
        super(company, project);
    }
}
