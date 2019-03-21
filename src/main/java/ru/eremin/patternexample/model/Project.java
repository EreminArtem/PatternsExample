package ru.eremin.patternexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @autor Eremin Artem on 20.03.2019.
 */

@Getter
@Setter
@NoArgsConstructor
public class Project {

    private String name = "Project" + UUID.randomUUID().toString();

    private boolean start = false;

    private boolean backendFinish = false;

    private boolean frontendFinish = false;

    private Language backend;

    private Language frontend;

    private Company performer;

    public Project(final Language backend, final Language frontend) {
        this.backend = backend;
        this.frontend = frontend;
    }
}
