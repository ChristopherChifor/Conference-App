package Presenters;

import ui.AppWindow;
import ui.view.View;

import java.util.Stack;

/**
 * The main presenter of the program. Stores a history of other presenters in the form of
 * a stack. Encapsulates the AppWindow class.
 */
public class MainPresenter implements Presenter{
    private AppWindow window;
    private Stack<Presenter> history = new Stack<>();

    /**
     * Constructor.
     */
    public MainPresenter(){
        window = new AppWindow(this);
    }

    /**
     * Adds presenter to the stack of presenters (history); calls window to show its view.
     * @param presenter presenter being added.
     */
    public void addPresenter(Presenter presenter){
        System.out.println("Adding presenter");
        history.push(presenter);
        window.showView(presenter.makeView());
    }

    /**
     * Pops the current presenter off the stack (history). Shows the next presenter in the stack
     * (the one that was added before). There are no more presenters in the history, the program
     * quits.
     */
    public void back(){
        if(history.size() <=1){
            System.exit(0);
        }
        history.pop();
        Presenter currentPresenter = history.peek();
        window.showView(currentPresenter.makeView());
    }

    @Override
    public View makeView() {
        return null;
    }

    @Override
    public MainPresenter getMainPresenter() {
        return this;
    }
}
