package drawing.command;

import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesBackward implements ICommand {

	final CanvasModel model;

	public UpdateModelSelectedShapesBackward(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.moveSelectedShapesBackward();
	}

	@Override
	public void undo() {
		model.moveSelectedShapesForward();
	}

}
