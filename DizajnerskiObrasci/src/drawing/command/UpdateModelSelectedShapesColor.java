package drawing.command;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesColor implements ICommand {

	private final CanvasModel model;
	private final ArrayList<Color> initialSelectedShapesColors;
	private final Color updateColor;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesColor(CanvasModel model, Color updateColor) {
		this.model = model;
		this.updateColor = updateColor;
		this.initialSelectedShapesColors = new ArrayList<>();
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;
		
		ArrayList<Shape> shapes = model.getAllSelectedShapes();

		for (int i = 0; i < shapes.size(); i++) {
			initialSelectedShapesColors.add(shapes.get(i).getColor());
		}

		model.updateColorOfSelectedShapes(updateColor);
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.updateShapeColor(selectedShapes.get(i), initialSelectedShapesColors.get(i));
		}
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("updateColor=").append(updateColor.toString())
				.append("; ").append("initialColors=").append(initialSelectedShapesColors.toString()).append(">");

		return output.toString();
	}

}
