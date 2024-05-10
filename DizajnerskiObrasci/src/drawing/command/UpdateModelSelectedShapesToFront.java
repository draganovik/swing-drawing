package drawing.command;

import drawing.mvc.CanvasModel;

public class UpdateModelSelectedShapesToFront implements ICommand {

	final CanvasModel model;

	public UpdateModelSelectedShapesToFront(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.moveSelectedShapesToFront();
	}

	@Override
	public void undo() {
		// ??
	}

}
