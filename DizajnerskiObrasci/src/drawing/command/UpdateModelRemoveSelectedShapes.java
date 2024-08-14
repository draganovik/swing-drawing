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
		model.removeSelectedShapes();
	}

	@Override
	public void undo() {
		selectedShapes.forEach(entry -> model.insertShape(entry.getValue(), entry.getKey()));
	}

}
