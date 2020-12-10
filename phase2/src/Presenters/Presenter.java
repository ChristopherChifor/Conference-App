package Presenters;

import ui.view.View;

/**
 * Interface for Presenters
 */
public interface Presenter {
    /**
     * Constructs a new view for this presenter
     * @return view of this presenter.
     */
    View makeView();

    /**
     * Getter for the MainPresenter (used to show a new view/presenter)
     * @return the main presenter.
     */
    MainPresenter getMainPresenter();
}
