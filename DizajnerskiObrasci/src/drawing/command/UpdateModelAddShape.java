package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelAddShape implements ICommand {

	final CanvasModel model;
	final Shape shape;
	private Boolean isExecuted = false;

	public UpdateModelAddShape(CanvasModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		model.addShape(shape);
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.removeShape(shape);
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("shape=").append(shape.toString()).append(">");

		return output.toString();
	}

}
