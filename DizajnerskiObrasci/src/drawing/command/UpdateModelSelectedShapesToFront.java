package drawing.command;

import java.util.ArrayList;
import java.util.Collections;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesToFront implements ICommand {

	final CanvasModel model;
	final ArrayList<Integer> initialSelectedShapesOrder;

	public UpdateModelSelectedShapesToFront(CanvasModel model) {
		this.model = model;
		this.initialSelectedShapesOrder = model.getAllSelectedShapeIndexes();
		Collections.sort(initialSelectedShapesOrder);
	}

	@Override
	public void execute() {
		model.moveSelectedShapesToFront();
	}

	@Override
	public void undo() {
		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = selectedShapes.size(); --i >= 0;) {
			model.removeShape(selectedShapes.get(i));
			model.insertShape(selectedShapes.get(i), this.initialSelectedShapesOrder.get(i));
		}
	}

}
