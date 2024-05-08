package drawing.command;

import drawing.mvc.CanvasModel;

public class UpdateModelSelectedShapesToBack implements ICommand {

	final CanvasModel model;

	public UpdateModelSelectedShapesToBack(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.moveSelectedShapesToBack();
	}

	@Override
	public void undo() {
		model.moveSelectedShapesToFront();
	}

}
