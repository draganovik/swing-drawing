package drawing;

import com.formdev.flatlaf.FlatLightLaf;

import drawing.mvc.DrawingController;
import drawing.mvc.DrawingFrame;
import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.WorkspaceModel;

public class Application {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		FlatLightLaf.setup();

		DrawingFrame drawingFrame = new DrawingFrame();

		// Initialize Models
		CanvasModel canvasModel = new CanvasModel();
		WorkspaceModel workspaceModel = new WorkspaceModel();

		// Setup Controller
		DrawingController drawingController = new DrawingController(canvasModel, workspaceModel);

		// Initialize Views
		drawingFrame.setupViews(canvasModel, workspaceModel, drawingController);
		drawingController.setViews(drawingFrame.getCanvasView(), drawingFrame.getToolbarView(),
				drawingFrame.getMenubarView(), drawingFrame.getCanvasShapesPanelView());

		// Show Frame
		drawingFrame.setVisible(true);
	}

}
