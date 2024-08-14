package drawing;

import drawing.mvc.DrawingController;
import drawing.mvc.DrawingFrame;
import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.ToolbarModel;

public class Application {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		DrawingFrame drawingFrame = new DrawingFrame();

		// Initialize Models
		CanvasModel canvasModel = new CanvasModel();
		ToolbarModel toolbarModel = new ToolbarModel();

		// Setup Controller
		DrawingController drawingController = new DrawingController(canvasModel, toolbarModel);

		// Initialize Views
		drawingFrame.setupCanvas(canvasModel, toolbarModel, drawingController);

		// Show Frame
		drawingFrame.setVisible(true);
	}

}
