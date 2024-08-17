package drawing.command;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesBackgroundColor implements ICommand {

	private final CanvasModel model;
	private final ArrayList<Color> initialSelectedShapesBackgroundColors;
	private final Color updateColor;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesBackgroundColor(CanvasModel model, Color updateColor) {
		this.model = model;
		this.updateColor = updateColor;
		this.initialSelectedShapesBackgroundColors = new ArrayList<>();
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;
		
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
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.updateShapeBackgroundColor(selectedShapes.get(i), initialSelectedShapesBackgroundColors.get(i));
		}
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("updateColor=").append(updateColor.toString())
				.append("; ").append("initialColors=").append(initialSelectedShapesBackgroundColors.toString())
				.append(">");

		return output.toString();
	}

}
