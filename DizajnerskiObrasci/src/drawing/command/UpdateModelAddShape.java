package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelAddShape implements ICommand {

	final CanvasModel model;
	final Shape shape;

	public UpdateModelAddShape(CanvasModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void undo() {
		model.removeShape(shape);
	}

}
