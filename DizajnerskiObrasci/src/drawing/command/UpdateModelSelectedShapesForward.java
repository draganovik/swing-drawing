package drawing.command;

import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesForward implements ICommand {

	final CanvasModel model;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesForward(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		model.moveSelectedShapesForward();
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.moveSelectedShapesBackward();
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command);

		return output.toString();
	}

}
