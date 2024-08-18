package drawing.command;

import drawing.geometry.Point;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class UpdateModelSelectedShapesPosition implements ICommand {

	private final CanvasModel model;
	private final Point startPoint;
	private final Point endPoint;

	private CommandState state = CommandState.INITIALIZED;

	public UpdateModelSelectedShapesPosition(CanvasModel model, Point startPoint, Point endPoint) {
		this.model = model;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	@Override
	public void execute() {
		if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
			throw new IllegalStateException("Command is already executed.");
		}
		state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

		int moveByX = endPoint.getX() - startPoint.getX();
		int moveByY = endPoint.getY() - startPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);

	}

	@Override
	public void undo() {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		int moveByX = startPoint.getX() - endPoint.getX();
		int moveByY = startPoint.getY() - endPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command).append(" <").append("startPoint=")
				.append(startPoint.toString()).append("; ").append("endPoint=").append(endPoint.toString()).append(">");

		return output.toString();
	}

}
