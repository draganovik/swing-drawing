package drawing.command;

import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class SelectShape implements ICommand {

    private final CanvasModel model;
    private final Integer selectedShapeIndex;

    private CommandState state = CommandState.INITIALIZED;

    public SelectShape(CanvasModel model, Integer shapeIndex) {
        this.model = model;
        this.selectedShapeIndex = shapeIndex;
    }

    @Override
    public void execute() {
        if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
            throw new IllegalStateException("Command is already executed.");
        }
        state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

        model.selectShapeAt(selectedShapeIndex);
    }

    @Override
    public void undo() {
        if (state != CommandState.EXECUTE && state != CommandState.REDO) {
            throw new IllegalStateException("Command is not executed.");
        }
        state = CommandState.UNDO;

        model.deselectShapeAt(selectedShapeIndex);
    }

    @Override
    public String toString() {
        String command = this.getClass().getSimpleName();

        StringBuilder output = new StringBuilder();
        output.append(state.toString()).append(" ").append(command).append(" <").append("selectedShapeIndex=")
                .append(selectedShapeIndex.toString()).append(">");

        return output.toString();
    }

}
