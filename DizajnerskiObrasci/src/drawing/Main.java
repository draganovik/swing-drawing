package drawing;

import javax.swing.WindowConstants;

import drawing.components.canvas.CanvasController;
import drawing.components.canvas.CanvasModel;
import drawing.components.menubar.MenubarController;
import drawing.components.toolbar.ToolbarController;
import drawing.components.toolbar.ToolbarModel;

public class Main {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		ApplicationFrame frame = new ApplicationFrame();

		CanvasModel canvasModel = new CanvasModel();
		CanvasController canvasController = new CanvasController(canvasModel);
		frame.setupCanvas(canvasModel, canvasController);

		ToolbarModel toolbarModel = new ToolbarModel();
		ToolbarController toolbarController = new ToolbarController(toolbarModel);
		frame.setupToolbar(toolbarModel, toolbarController);

		toolbarController.setCanvasViewModel(frame.getCanvasView(), canvasModel);
		canvasController.setToolbarModelController(toolbarModel, toolbarController);

		frame.setupTabPanels(canvasModel);

		MenubarController menubarController = new MenubarController(canvasModel);
		menubarController.setCanvasView(frame.getCanvasView());
		frame.setupManubar(menubarController);

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
