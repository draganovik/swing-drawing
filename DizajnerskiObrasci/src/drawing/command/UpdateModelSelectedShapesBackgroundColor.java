package drawing.command;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class UpdateModelSelectedShapesBackgroundColor implements ICommand {

	private final CanvasModel model;
	private final ArrayList<Color> initialSelectedShapesBackgroundColors = new ArrayList<>();
	private final Color updateColor;

	private CommandState state = CommandState.INITIALIZED;

	public UpdateModelSelectedShapesBackgroundColor(CanvasModel model, Color updateColor) {
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
			if (shapes.get(i) instanceof SurfaceShape) {
				initialSelectedShapesBackgroundColors.add(((SurfaceShape) shapes.get(i)).getBackgroundColor());
			} else {
				initialSelectedShapesBackgroundColors.add(null);
			}
		}

		model.updateBackgroundColorOfSelectedShapes(updateColor);
	}

	@Override
	public void undo() {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.updateShapeBackgroundColor(selectedShapes.get(i), initialSelectedShapesBackgroundColors.get(i));
		}
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command).append(" <").append("updateColor=")
				.append(updateColor.toString()).append("; ").append("initialColors=")
				.append(initialSelectedShapesBackgroundColors.toString()).append(">");

		return output.toString();
	}

}
