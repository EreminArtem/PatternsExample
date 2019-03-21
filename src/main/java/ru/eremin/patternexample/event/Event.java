package ru.eremin.patternexample.event;

import lombok.Getter;
import lombok.Setter;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Project;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

@Getter
@Setter
public class Event {

    private Company company;

    private Project project;

    public Event(final Company company, final Project project) {
        this.company = company;
        this.project = project;
    }
}
