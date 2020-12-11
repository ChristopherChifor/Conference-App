package Presenters;

import UseCases.AccountManager;
import Util.UserType;
import ui.view.OrganizerView;
import ui.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for organizer
 */
public class OrganizerPresenter implements Presenter {
    private MainPresenter mainPresenter;
    private AccountManager accountManager = new AccountManager();

    public OrganizerPresenter(MainPresenter presenter) {
        this.mainPresenter = presenter;
    }

    /**
     * Getter for list of all attendees in the conference app
     *
     * @return list of all usernames of attendees
     */
    public List<String> getAttendees() {
        return accountManager.getUsernamesOfType(UserType.ATTENDEE);
    }

    /**
     * Getter for list of all speakers in the conference app
     *
     * @return list of all usernames of speakers
     */
    public List<String> getSpeakers() {
        return accountManager.getUsernamesOfType(UserType.SPEAKER);
    }

    /**
     * Getter for list of all organizers in the conference app
     *
     * @return list of all usernames of organizers
     */
    public List<String> getOrganizers() {
        return accountManager.getUsernamesOfType(UserType.ORGANIZER);
    }

    /**
     * Getter for list of all VIPs in the conference app
     *
     * @return list of all usernames of VIPs
     */
    public List<String> getVips() {
        return accountManager.getUsernamesOfType(UserType.VIP);
    }

    /**
     * Opens user settings presenter (in organizer mode) of this user.
     *
     * @param username the username of user.
     */
    public void goToUser(String username) {
        UserSettingsPresenter usp = new UserSettingsPresenter(true, username, mainPresenter);
        mainPresenter.addPresenter(usp);
    }

    /**
     * Creates a new room if allowed.
     * @param roomName name of new room
     * @param roomCapacity capacity of new room
     * @param view view used to show any error messages
     */
    public void newRoom(String roomName, int roomCapacity, View view){
        //TODO MAKE NEW ROOM
        //USE view.showIncorrectInputDialog() to notify users of bad input.
    }

    /**
     * Takes organizer to EventEditPresenter to make a new event.
     */
    public void newEvent(){
        EventEditPresenter ep = new EventEditPresenter(mainPresenter);
        mainPresenter.addPresenter(ep);
    }

    @Override
    public View makeView() {
        return new OrganizerView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
