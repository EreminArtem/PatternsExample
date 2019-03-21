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

@NoArgsConstructor
public class Developer {

    @Getter
    @Setter
    private String name = "Developer" + UUID.randomUUID().toString();

    @Getter
    @Setter
    private boolean hired = false;

    @Getter
    @Setter
    private boolean busy = false;

    private List<Language> backend = new ArrayList<>();

    private List<Language> frontend = new ArrayList<>();

    public Developer(final List<Language> backend, final List<Language> frontend) {
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
}
