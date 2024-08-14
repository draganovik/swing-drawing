package drawing.command;

import java.util.ArrayList;

import drawing.mvc.models.CanvasModel;

public class UpdateModelDuplicateSelectedShape implements ICommand {

	final CanvasModel model;
	final ArrayList<Integer> selectedShapesPositions;

	public UpdateModelDuplicateSelectedShape(CanvasModel model) {
		this.model = model;
		this.selectedShapesPositions = model.getAllSelectedShapeIndexes();
	}

	@Override
	public void execute() {
		model.duplicateSelected();
	}

	@Override
	public void undo() {
		model.removeSelectedShapes();
		for (int index = 0; index < this.selectedShapesPositions.size(); index++) {
			model.selectShapeAt(selectedShapesPositions.get(index));
		}
	}

}
