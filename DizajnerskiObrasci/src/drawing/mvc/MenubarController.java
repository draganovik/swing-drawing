package drawing.mvc;

import drawing.mvc.views.CanvasView;

public class MenubarController {
	CanvasModel canvasModel;
	CanvasView canvasView;
	CanvasController canvasController;

	public MenubarController(CanvasModel canvasModel) {
		this.canvasModel = canvasModel;
	}

	public void setCanvasViewController(CanvasView canvasView, CanvasController canvasController) {
		this.canvasView = canvasView;
		this.canvasController = canvasController;
	}

	public void moveForward() {
		canvasModel.moveSelectedShapesForward();
		canvasView.repaint();
	}

	public void moveBackward() {
		canvasModel.moveSelectedShapesBackward();
		canvasView.repaint();
	}

	public void moveToFront() {
		canvasModel.moveSelectedShapesToFront();
		canvasView.repaint();
	}

	public void moveToBack() {
		canvasModel.moveSelectedShapesToBack();
		canvasView.repaint();
	}

	public void duplicateSelected() {
		canvasModel.duplicateSelected();
		canvasView.repaint();

	}

	public void undo() {
		canvasController.undo();

	}

	public void redo() {
		canvasController.redo();

	}

}
