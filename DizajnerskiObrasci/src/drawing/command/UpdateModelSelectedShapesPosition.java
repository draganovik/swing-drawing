package drawing.command;

import drawing.geometry.Point;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesPosition implements ICommand {

	final CanvasModel model;
	final Point startPoint;
	final Point endPoint;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapesPosition(CanvasModel model, Point startPoint, Point endPoint) {
		this.model = model;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		int moveByX = endPoint.getX() - startPoint.getX();
		int moveByY = endPoint.getY() - startPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);

	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		int moveByX = startPoint.getX() - endPoint.getX();
		int moveByY = startPoint.getY() - endPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("startPoint=").append(startPoint.toString())
				.append(", ").append("endPoint=").append(endPoint.toString()).append(">");

		return output.toString();
	}

}
