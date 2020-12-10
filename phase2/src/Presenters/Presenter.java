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
}
