package ru.eremin.patternexample.process;

import lombok.Getter;
import ru.eremin.patternexample.event.Event;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

@Getter
public class StartProductionEvent extends Event {

    public StartProductionEvent(final Company company, final Project project) {
        super(company, project);
    }
}
