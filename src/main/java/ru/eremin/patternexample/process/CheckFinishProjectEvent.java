package ru.eremin.patternexample.process;

import lombok.Getter;
import ru.eremin.patternexample.event.Event;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

@Getter
public class CheckFinishProjectEvent extends Event {

    public CheckFinishProjectEvent(final Company company, final Project project) {
        super(company, project);
    }
}
