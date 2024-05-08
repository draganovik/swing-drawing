package drawing.components.menubar;

import drawing.components.canvas.CanvasModel;
import drawing.components.canvas.CanvasView;

public class MenubarController {
	CanvasModel canvasModel;
	CanvasView canvasView;

	public MenubarController(CanvasModel canvasModel) {
		this.canvasModel = canvasModel;
	}

	public void setCanvasView(CanvasView canvasView) {
		this.canvasView = canvasView;
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

}
