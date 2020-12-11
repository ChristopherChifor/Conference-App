package Presenters;

import Controllers.AccountController;
import Controllers.RoomController;
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
    private RoomController roomController;
    private MainPresenter mainPresenter;
    private AccountController accountController;

    public OrganizerPresenter(MainPresenter presenter) {
        this.roomController = new RoomController();
        this.mainPresenter = presenter;
        this.accountController = new AccountController();
    }

    /**
     * Getter for list of all attendees in the conference app
     *
     * @return list of all usernames of attendees
     */
    public List<String> getAttendees() {
        return accountController.getUsernamesOfType(UserType.ATTENDEE);
    }

    /**
     * Getter for list of all speakers in the conference app
     *
     * @return list of all usernames of speakers
     */
    public List<String> getSpeakers() {
        return accountController.getUsernamesOfType(UserType.SPEAKER);
    }

    /**
     * Getter for list of all organizers in the conference app
     *
     * @return list of all usernames of organizers
     */
    public List<String> getOrganizers() {
        return accountController.getUsernamesOfType(UserType.ORGANIZER);
    }

    /**
     * Getter for list of all VIPs in the conference app
     *
     * @return list of all usernames of VIPs
     */
    public List<String> getVips() {
        return accountController.getUsernamesOfType(UserType.VIP);
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
        if (!roomController.createRoom(roomName, roomCapacity)) view.showIncorrectInputDialog("Invalid input.");
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
