package drawing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import drawing.mvc.DrawingController;
import drawing.mvc.DrawingFrame;
import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.WorkspaceModel;

public class Application {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DrawingFrame drawingFrame = new DrawingFrame();

		// Initialize Models
		CanvasModel canvasModel = new CanvasModel();
		WorkspaceModel workspaceModel = new WorkspaceModel();

		// Setup Controller
		DrawingController drawingController = new DrawingController(canvasModel, workspaceModel);

		// Initialize Views
		drawingFrame.setupViews(canvasModel, workspaceModel, drawingController);
		drawingController.setViews(drawingFrame.getCanvasView(), drawingFrame.getToolbarView(),
				drawingFrame.getMenubarView());

		// Show Frame
		drawingFrame.setVisible(true);
	}

}
