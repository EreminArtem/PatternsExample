package ru.eremin.patternexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eremin.patternexample.annotation.Logger;
import ru.eremin.patternexample.annotation.Profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

@NoArgsConstructor
public class Company implements ICompany{

    @Getter
    @Setter
    private String name = "Company" + UUID.randomUUID().toString();

    private List<Language> backend = new ArrayList<>();

    private List<Language> frontend = new ArrayList<>();

    private List<Developer> backendDevelopers = new ArrayList<>();

    private List<Developer> frontendDevelopers = new ArrayList<>();

    private List<Developer> fullStackDevelopers = new ArrayList<>();

    private List<Project> projects = new ArrayList<>();

    public Company(final List<Language> backend, final List<Language> frontend) {
        if (backend != null && !backend.isEmpty()) this.backend = new ArrayList<>(backend);
        if (frontend != null && !frontend.isEmpty()) this.frontend = new ArrayList<>(frontend);
    }

    public void addToBackend(final Language language) {
        backend.add(language);
    }

    public void addToFrontend(final Language language) {
        frontend.add(language);
    }

    public List<Language> getBackend() {
        return new ArrayList<>(backend);
    }

    public List<Language> getFrontend() {
        return new ArrayList<>(frontend);
    }

    @Override
    public boolean interview(final Developer developer) {
        boolean backendSpecialist = false;
        boolean frontendSpecialist = false;
        for (final Language language : backend) {
            if (developer.getBackend().contains(language)) backendSpecialist = true;
        }
        for (final Language language : frontend) {
            if (developer.getFrontend().contains(language)) frontendSpecialist = true;
        }

        if (backendSpecialist && frontendSpecialist) {
            hireFullStackDeveloper(developer);
            return true;
        } else if (backendSpecialist) {
            hireBackendDeveloper(developer);
            return true;
        } else if (frontendSpecialist) {
            hireFrontendDeveloper(developer);
            return true;
        }
        return false;
    }

    private void hireFullStackDeveloper(final Developer developer) {
        developer.setHired(true);
        this.fullStackDevelopers.add(developer);
    }

    private void hireBackendDeveloper(final Developer developer) {
        developer.setHired(true);
        this.backendDevelopers.add(developer);
    }

    private void hireFrontendDeveloper(final Developer developer) {
        developer.setHired(true);
        this.fullStackDevelopers.add(developer);
    }

    @Override
    public boolean checkProject(final Project project) {
        return backend.contains(project.getBackend()) && frontend.contains(project.getFrontend());
    }

    public void addProject(final Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }

    @Override
    public Developer getDeveloperForBackend(final Project project) {
        for (final Developer backendDeveloper : backendDevelopers) {
            if(backendDeveloper.getBackend().contains(project.getBackend())){
                backendDeveloper.setBusy(true);
                return backendDeveloper;
            }
        }
        return null;
    }

    @Override
    public Developer getDeveloperForFrontend(final Project project) {
        for (final Developer frontendDeveloper : frontendDevelopers) {
            if(frontendDeveloper.getBackend().contains(project.getFrontend())){
                frontendDeveloper.setBusy(true);
                return frontendDeveloper;
            }
        }
        return null;
    }
}
