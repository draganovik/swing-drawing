package drawing.command;

import java.util.ArrayList;

import drawing.mvc.CanvasModel;

public class UpdateModelShapeDeselectAll implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> selectedIndexList;

	public UpdateModelShapeDeselectAll(CanvasModel model) {
		this.model = model;
		this.selectedIndexList = model.getAllSelectedShapeIndexes();
	}

	@Override
	public void execute() {
		model.deselectAllShapes();
	}

	@Override
	public void undo() {
		model.deselectAllShapes();
		for (int i = 0; i < selectedIndexList.size(); i++) {
			model.selectShapeAt(selectedIndexList.get(i));
		}
	}

}
