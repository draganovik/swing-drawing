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
		canvasController.moveSelectionForward();
	}

	public void moveBackward() {
		canvasController.moveSelectionBackward();
	}

	public void moveToFront() {
		canvasController.moveSelectionToFront();
	}

	public void moveToBack() {
		canvasController.moveSelectionToBack();
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
