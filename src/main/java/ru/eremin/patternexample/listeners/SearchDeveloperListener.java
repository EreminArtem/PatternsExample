package ru.eremin.patternexample.listeners;

import ru.eremin.patternexample.dispatcher.Dispatcher;
import ru.eremin.patternexample.dispatcher.Listener;
import ru.eremin.patternexample.hire.FinishSearchDeveloperEvent;
import ru.eremin.patternexample.hire.SearchDeveloperEvent;
import ru.eremin.patternexample.model.Developer;
import ru.eremin.patternexample.model.DeveloperExchange;

import java.util.List;

/**
 * @autor Eremin Artem on 21.03.2019.
 */

public class SearchDeveloperListener  implements Listener<SearchDeveloperEvent> {

    @Override
    public void listen(final SearchDeveloperEvent event) {
        List<Developer> developers = DeveloperExchange.getInstance().getDevelopers();
        if(developers == null) return;
        for (final Developer developer : developers) {
            if (developer.getBackend().contains(event.getProject().getBackend()) && !developer.isHired()){
                if(event.getCompany().interview(developer)) {
                    Dispatcher.getInstance().asyncFire(new FinishSearchDeveloperEvent(event.getCompany(), event.getProject()));
                    return;
                }
            }
        }
        for (final Developer developer : developers) {
            if (developer.getFrontend().contains(event.getProject().getFrontend()) && !developer.isHired()){
                if(event.getCompany().interview(developer)) {
                    Dispatcher.getInstance().asyncFire(new FinishSearchDeveloperEvent(event.getCompany(), event.getProject()));
                    return;
                }
            }
        }

    }
}
