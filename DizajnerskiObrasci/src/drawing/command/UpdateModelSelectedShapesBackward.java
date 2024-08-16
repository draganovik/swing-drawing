package drawing.command;

import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesBackward implements ICommand {

	final CanvasModel model;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesBackward(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		model.moveSelectedShapesBackward();
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.moveSelectedShapesForward();
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
