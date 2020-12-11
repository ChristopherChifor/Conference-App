package Presenters;

import Controllers.EventController;
import ui.view.EventListView;
import ui.view.View;

import java.util.List;

public class EventListPresenter implements Presenter{
    private List<String> eventNames;
    private List<String> vipEvents;
    private MainPresenter mainPresenter;

    public EventListPresenter(boolean vipFilter, MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        EventController ec = new EventController();
        eventNames = ec.getEventNames(vipFilter);
        vipEvents = ec.getVIPEventNames();
    }

    public List<String> getEventNames() {
        return eventNames;
    }

    /**
     * Checks if event name is name of vip event
     * @param event event name
     * @return true iff the event is VIP only.
     */
    public boolean isVIP(String event){
        return vipEvents.contains(event);
    }

    /**
     * Goes to EventView for this event. (assumes it exists0
     * @param event name of event
     */
    public void goToEvent(String event){
        //todo
    }

    @Override
    public View makeView() {
        return new EventListView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
