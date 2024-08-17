package drawing.command;

import java.util.ArrayList;
import java.util.Collections;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesToBack implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> initialSelectedShapesOrder;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesToBack(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		this.initialSelectedShapesOrder = model.getAllSelectedShapeIndexes();
		Collections.sort(initialSelectedShapesOrder);

		model.moveSelectedShapesToBack();
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = selectedShapes.size(); --i >= 0;) {
			model.removeShape(selectedShapes.get(i));
			model.insertShape(selectedShapes.get(i), this.initialSelectedShapesOrder.get(i));
		}
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("initialSelectedShapesOrder=")
				.append(initialSelectedShapesOrder.toString()).append(">");

		return output.toString();
	}

}
