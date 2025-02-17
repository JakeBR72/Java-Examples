/**
 * Controller class.
 *
 * @author Jacob Ruth
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        /*
         * Update output and undo button
         */
        view.updateUndoAllowed(model.output().length() > 0);
        view.updateInputDisplay(input);
        String output = "";
        for (String item : model.output()) {
            output = output.concat(item);
        }
        /*
         * Update view to reflect changes in model
         */
        view.updateOutputDisplay(output);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.output().clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes copy event.
     *
     * @param input
     *            value of input text (provided by view)
     */
    @Override
    public void processAppendEvent(String input) {

        /*
         * Update model in response to this event
         */
        this.model.setInput(input);
        this.model.output().flip();
        this.model.output().push(input);
        this.model.output().flip();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processUndoEvent() {
        this.model.output().flip();
        this.model.output().pop();
        this.model.output().flip();
        updateViewToMatchModel(this.model, this.view);

    }

}
