package Presenters;

import Controllers.EventController;
import Util.UserType;
import ui.view.EventListView;
import ui.view.View;

import java.util.List;

public class EventListPresenter implements Presenter {
    private List<String> eventNames;
    private List<String> vipEvents;
    private MainPresenter mainPresenter;
    private String username;
    private UserType type;

    /**
     *  Constructor
     * @param username username
     * @param type type of user
     * @param mainPresenter main presenter
     */
    public EventListPresenter(String username, UserType type, MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.username = username;
        this.type = type;
        EventController ec = new EventController();
        boolean vipFilter = type == UserType.VIP || type == UserType.ORGANIZER;
        eventNames = ec.getEventNames(vipFilter);
        vipEvents = ec.getVIPEventNames();
    }

    /**
     * gets event names
     * @return event names
     */
    public List<String> getEventNames() {
        return eventNames;
    }

    /**
     * Checks if event name is name of vip event
     *
     * @param event event name
     * @return true iff the event is VIP only.
     */
    public boolean isVIP(String event) {
        return vipEvents.contains(event);
    }

    /**
     * Goes to EventView for this event. (assumes it exists0
     *
     * @param event name of event
     */
    public void goToEvent(String event) {
        EventPresenter ep = new EventPresenter(event, username, mainPresenter,type);
        mainPresenter.addPresenter(ep);
    }

    /**
     * Makes a view
     * @return view it makes
     */
    @Override
    public View makeView() {
        return new EventListView(this);
    }

    /**
     * gets main presenter
     * @return main presenter
     */
    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
