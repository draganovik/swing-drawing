package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

import java.awt.*;
import java.util.ArrayList;

public class UpdateSelectedShapeColor implements ICommand {

    private final CanvasModel model;
    private final ArrayList<Color> initialSelectedShapesColors = new ArrayList<>();
    private final Color updateColor;

    private CommandState state = CommandState.INITIALIZED;

    public UpdateSelectedShapeColor(CanvasModel model, Color updateColor) {
        this.model = model;
        this.updateColor = updateColor;
    }

    @Override
    public void execute() {
        if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
            throw new IllegalStateException("Command is already executed.");
        }
        state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

        ArrayList<Shape> shapes = model.getAllSelectedShapes();

        for (int i = 0; i < shapes.size(); i++) {
            initialSelectedShapesColors.add(shapes.get(i).getColor());
        }

        model.updateColorOfSelectedShapes(updateColor);
    }

    @Override
    public void undo() {
        if (state != CommandState.EXECUTE && state != CommandState.REDO) {
            throw new IllegalStateException("Command is not executed.");
        }
        state = CommandState.UNDO;

        ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
        for (int i = 0; i < selectedShapes.size(); i++) {
            model.updateShapeColor(selectedShapes.get(i), initialSelectedShapesColors.get(i));
        }
    }

    @Override
    public String toString() {
        String command = this.getClass().getSimpleName();

        StringBuilder output = new StringBuilder();
        output.append(state.toString()).append(" ").append(command).append(" <").append("updateColor=")
                .append(updateColor.toString()).append("; ").append("initialColors=")
                .append(initialSelectedShapesColors.toString()).append(">");

        return output.toString();
    }

}
