package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.CanvasModel;

public class UpdateModelShapeSelect implements ICommand {

	private final CanvasModel model;
	private final Shape shape;

	public UpdateModelShapeSelect(CanvasModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.selectShape(shape);
	}

	@Override
	public void undo() {
		model.deselectShape(shape);
	}

}
