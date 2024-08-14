package drawing;

import drawing.mvc.DrawingController;
import drawing.mvc.CanvasModel;
import drawing.mvc.FrameView;
import drawing.mvc.ToolbarModel;

public class Application {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		FrameView frameView = new FrameView();

		// Setup Models
		CanvasModel canvasModel = new CanvasModel();
		ToolbarModel toolbarModel = new ToolbarModel();
		
		// Setup Controller
		DrawingController drawingController = new DrawingController(canvasModel, toolbarModel);
		
		// Initialize Views
		frameView.setupCanvas(canvasModel, toolbarModel, drawingController);

		// Start application
		frameView.setVisible(true);
	}

}
