package drawing.command;

import drawing.geometry.Point;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesPosition implements ICommand {

	final CanvasModel model;
	final Point startPoint;
	final Point endPoint;

	public UpdateModelSelectedShapesPosition(CanvasModel model, Point startPoint, Point endPoint) {
		this.model = model;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	@Override
	public void execute() {
		int moveByX = endPoint.getX() - startPoint.getX();
		int moveByY = endPoint.getY() - startPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);

	}

	@Override
	public void undo() {
		int moveByX = startPoint.getX() - endPoint.getX();
		int moveByY = startPoint.getY() - endPoint.getY();
		model.moveSelectedShapesBy(moveByX, moveByY);
	}

}
