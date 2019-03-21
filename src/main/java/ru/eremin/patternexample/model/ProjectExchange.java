package ru.eremin.patternexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

public class ProjectExchange {

    private List<Project> projects = new ArrayList<>();

    private static final ProjectExchange INSTANCE = new ProjectExchange();

    public static ProjectExchange getInstance() {
        return INSTANCE;
    }

    private ProjectExchange() {
    }

    public void addProject(final Project project) {
        if (projects != null) projects.add(project);
    }

    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }
}
