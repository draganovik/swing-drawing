package drawing.command;

import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelRemoveSelectedShapes implements ICommand {

	final CanvasModel model;
	final ArrayList<Integer> deletedShapesIndexes;
	final ArrayList<Shape> deletedShapes;

	public UpdateModelRemoveSelectedShapes(CanvasModel model) {
		this.model = model;
		this.deletedShapes = model.getAllSelectedShapes();
		this.deletedShapesIndexes = model.getAllSelectedShapeIndexes();
	}

	@Override
	public void execute() {
		model.removeSelectedShapes();
	}

	@Override
	public void undo() {
		for (int i = 0; i < this.deletedShapesIndexes.size(); i++) {
			model.insertShape(deletedShapes.get(i), deletedShapesIndexes.get(i));
		}
	}

}
