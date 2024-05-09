package drawing.command;

import drawing.mvc.CanvasModel;

public class UpdateModelSelectedShapesForward implements ICommand {

	final CanvasModel model;

	public UpdateModelSelectedShapesForward(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.moveSelectedShapesForward();
	}

	@Override
	public void undo() {
		model.moveSelectedShapesBackward();
	}

}
