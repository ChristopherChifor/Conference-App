package Presenters;

import ui.view.OrganizerView;
import ui.view.View;

/**
 * Presenter for organizer
 */
public class OrganizerPresenter implements Presenter{
    private MainPresenter mainPresenter;

    public OrganizerPresenter(MainPresenter presenter) {
        this.mainPresenter = presenter;
    }

    @Override
    public View makeView() {
        return new OrganizerView();
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
