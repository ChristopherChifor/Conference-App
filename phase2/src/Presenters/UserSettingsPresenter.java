package Presenters;

import ui.view.UserSettingsView;
import ui.view.View;

public class UserSettingsPresenter implements Presenter{
    private boolean organizerMode;
    private String username;
    private MainPresenter mainPresenter;

    /**
     * Constructor
     * @param organizerMode is this presenter being accessed by organizer?
     * @param username username of user BEING accessed.
     */
    public UserSettingsPresenter(boolean organizerMode, String username, MainPresenter mainPresenter) {
        this.organizerMode = organizerMode;
        this.username = username;
        this.mainPresenter=mainPresenter;
    }

    @Override
    public View makeView() {
        return new UserSettingsView(this);
    }

    public boolean isOrganizerMode() {
        return organizerMode;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
