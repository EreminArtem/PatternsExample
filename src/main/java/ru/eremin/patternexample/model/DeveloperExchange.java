package ru.eremin.patternexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

public class DeveloperExchange {

    public List<Developer> developers = new ArrayList<>();

    private static final DeveloperExchange INSTANCE = new DeveloperExchange();

    public static DeveloperExchange getInstance() {
        return INSTANCE;
    }

    private DeveloperExchange() {
    }

    public void addDeveloper(final Developer developer) {
        if (developer != null) developers.add(developer);
    }

    public List<Developer> getDevelopers() {
        return new ArrayList<>(developers);
    }
}
