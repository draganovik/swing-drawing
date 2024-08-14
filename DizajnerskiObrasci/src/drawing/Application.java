package drawing;

import drawing.mvc.DrawingController;
import drawing.mvc.CanvasModel;
import drawing.mvc.FrameView;
import drawing.mvc.MenubarController;
import drawing.mvc.ToolbarController;
import drawing.mvc.ToolbarModel;

public class Application {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		FrameView frameView = new FrameView();

		// Setup Canvas
		CanvasModel canvasModel = new CanvasModel();
		DrawingController canvasController = new DrawingController(canvasModel);
		frameView.setupCanvas(canvasModel, canvasController);

		// Setup Toolbar
		ToolbarModel toolbarModel = new ToolbarModel();
		ToolbarController toolbarController = new ToolbarController(toolbarModel);
		frameView.setupToolbar(toolbarModel, toolbarController);

		// Toolbar <-> Canvas cross-references
		toolbarController.setCanvasViewModel(frameView.getCanvasView(), canvasModel);
		canvasController.setToolbarModelController(toolbarModel, toolbarController);

		// Setup Tab panels
		frameView.setupTabPanels(canvasModel);

		// Setup Menubar
		MenubarController menubarController = new MenubarController(canvasModel);
		frameView.setupManubar(menubarController);

		// Menubar Canvas references
		menubarController.setCanvasViewController(frameView.getCanvasView(), canvasController);

		// Start application
		frameView.setVisible(true);
	}

}
