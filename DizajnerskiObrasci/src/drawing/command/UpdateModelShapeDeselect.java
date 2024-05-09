package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.CanvasModel;

public class UpdateModelShapeDeselect implements ICommand {

	private final CanvasModel model;
	private final Shape shape;

	public UpdateModelShapeDeselect(CanvasModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.deselectShape(shape);
	}

	@Override
	public void undo() {
		model.selectShape(shape);
	}

}
