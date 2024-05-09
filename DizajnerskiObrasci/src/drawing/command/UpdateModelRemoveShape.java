package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.CanvasModel;

public class UpdateModelRemoveShape implements ICommand {

	final CanvasModel model;
	final Shape shape;

	public UpdateModelRemoveShape(CanvasModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.removeShape(shape);
	}

	@Override
	public void undo() {
		model.addShape(shape);
	}

}
