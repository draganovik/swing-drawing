package drawing.command;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelRemoveSelectedShapes implements ICommand {

	final CanvasModel model;
	final List<SimpleEntry<Integer, Shape>> selectedShapes; // Store deleted index and shape together

	private Boolean isExecuted = false;

	public UpdateModelRemoveSelectedShapes(CanvasModel model) {
		this.model = model;
		ArrayList<Shape> deletedShapes = model.getAllSelectedShapes();

		// Use a List of SimpleEntry to store index and shape pairs
		this.selectedShapes = new ArrayList<>();

		// Collect shapes and their respective indexes
		IntStream.range(0, deletedShapes.size()).forEach(i -> selectedShapes
				.add(new SimpleEntry<>(model.getShapeIndex(deletedShapes.get(i)), deletedShapes.get(i))));
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		model.removeSelectedShapes();
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		selectedShapes.forEach(entry -> model.insertShape(entry.getValue(), entry.getKey()));
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("selectedShapes=").append(selectedShapes.toString())
				.append(">");

		return output.toString();
	}

}
