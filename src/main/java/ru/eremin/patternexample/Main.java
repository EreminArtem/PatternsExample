package ru.eremin.patternexample;

import ru.eremin.patternexample.check_project.FinishCheckProjectsEvent;
import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.handlers.GlobalHandler;
import ru.eremin.patternexample.mediator.Mediator;
import ru.eremin.patternexample.model.Company;
import ru.eremin.patternexample.model.Developer;
import ru.eremin.patternexample.model.DeveloperExchange;
import ru.eremin.patternexample.model.ICompany;
import ru.eremin.patternexample.model.Language;
import ru.eremin.patternexample.model.Project;
import ru.eremin.patternexample.model.ProjectExchange;
import ru.eremin.patternexample.search_project.SearchProjectEvent;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class Main {

    public static void main(String[] args) {

        final Dispatcher dispatcher = Dispatcher.getInstance();
        final Mediator mediator = new Mediator(dispatcher);

        mediator.subscribe(FinishCheckProjectsEvent.class, (event -> {
            System.out.println("Project Finish");
        }));

        final Company javaCompany = (Company) create();
        final Company newCompany = new Company(Arrays.asList(Language.JAVA, Language.PHP), Arrays.asList(Language.JAVASCRIPT));
        final Company pythonCompany = new Company(Arrays.asList(Language.PYTHON), Arrays.asList(Language.PYTHON));

        final Developer developer1 = new Developer(Arrays.asList(Language.JAVA), Arrays.asList(Language.JAVASCRIPT));
        final Developer developer2 = new Developer(Arrays.asList(Language.JAVA, Language.PHP), Collections.emptyList());
        final Developer developer3 = new Developer(Arrays.asList(Language.PYTHON), Arrays.asList(Language.TYPESCRIPT));
        final Developer developer4 = new Developer(Arrays.asList(Language.PYTHON, Language.JAVASCRIPT), Arrays.asList(Language.JAVASCRIPT));
        final Developer developer5 = new Developer(Arrays.asList(Language.JAVA), Arrays.asList(Language.TYPESCRIPT));

        final Project project1 = new Project(Language.JAVA, Language.JAVA);
        final Project project2 = new Project(Language.PHP, Language.JAVASCRIPT);
        final Project project3 = new Project(Language.PYTHON, Language.TYPESCRIPT);

        DeveloperExchange.getInstance().addDeveloper(developer1);
        DeveloperExchange.getInstance().addDeveloper(developer2);
        DeveloperExchange.getInstance().addDeveloper(developer3);
        DeveloperExchange.getInstance().addDeveloper(developer4);
        DeveloperExchange.getInstance().addDeveloper(developer5);

        ProjectExchange.getInstance().addProject(project1);
        ProjectExchange.getInstance().addProject(project2);
        ProjectExchange.getInstance().addProject(project3);
        dispatcher.asyncFire(new SearchProjectEvent(javaCompany, null));
        dispatcher.shutdown();

    }

    public static ICompany create() {
        final ICompany javaCompany = new Company(Arrays.asList(Language.JAVA), Arrays.asList(Language.JAVA));
        return (ICompany) Proxy.newProxyInstance(
                Company.class.getClassLoader(), Company.class.getInterfaces(),
                new GlobalHandler(javaCompany));
    }

}
