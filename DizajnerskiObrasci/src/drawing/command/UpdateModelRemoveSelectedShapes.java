package drawing.command;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelRemoveSelectedShapes implements ICommand {

	private final CanvasModel model;
	private final List<SimpleEntry<Integer, Shape>> selectedShapes; // Store deleted index and shape together

	private Boolean isExecuted = false;

	public UpdateModelRemoveSelectedShapes(CanvasModel model) {
		this.model = model;

		// Use a List of SimpleEntry to store index and shape pairs
		this.selectedShapes = new ArrayList<>();
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;
		
		ArrayList<Shape> deletedShapes = model.getAllSelectedShapes();
		// Collect shapes and their respective indexes
		IntStream.range(0, deletedShapes.size()).forEach(i -> selectedShapes
				.add(new SimpleEntry<>(model.getShapeIndex(deletedShapes.get(i)), deletedShapes.get(i))));

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
