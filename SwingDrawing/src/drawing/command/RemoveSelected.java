package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RemoveSelected implements ICommand {

    private final CanvasModel model;
    private final List<SimpleEntry<Integer, Shape>> selectedShapes = new ArrayList<>(); // Store deleted index and shape
    // together

    private CommandState state = CommandState.INITIALIZED;

    public RemoveSelected(CanvasModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
            throw new IllegalStateException("Command is already executed.");
        }
        state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

        ArrayList<Shape> deletedShapes = model.getAllSelectedShapes();

        // Collect shapes and their respective indexes
        IntStream.range(0, deletedShapes.size()).forEach(i -> selectedShapes
                .add(new SimpleEntry<>(model.getShapeIndex(deletedShapes.get(i)), deletedShapes.get(i))));

        model.removeSelectedShapes();
    }

    @Override
    public void undo() {
        if (state != CommandState.EXECUTE && state != CommandState.REDO) {
            throw new IllegalStateException("Command is not executed.");
        }
        state = CommandState.UNDO;

        selectedShapes.forEach(entry -> model.insertShape(entry.getValue(), entry.getKey()));
    }

    @Override
    public String toString() {
        String command = this.getClass().getSimpleName();

        StringBuilder output = new StringBuilder();
        output.append(state.toString()).append(" ").append(command).append(" <").append("selectedShapes=")
                .append(selectedShapes.toString()).append(">");

        return output.toString();
    }

}
