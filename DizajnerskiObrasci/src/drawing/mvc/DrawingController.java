package drawing.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import drawing.adapters.HexagonAdapter;
import drawing.command.ICommand;
import drawing.command.UpdateModelAddShape;
import drawing.command.UpdateModelDuplicateSelectedShape;
import drawing.command.UpdateModelRemoveSelectedShapes;
import drawing.command.UpdateModelSelectedShapesBackgroundColor;
import drawing.command.UpdateModelSelectedShapesBackward;
import drawing.command.UpdateModelSelectedShapesColor;
import drawing.command.UpdateModelSelectedShapesForward;
import drawing.command.UpdateModelSelectedShapesPosition;
import drawing.command.UpdateModelSelectedShapesToBack;
import drawing.command.UpdateModelSelectedShapesToFront;
import drawing.command.UpdateModelShapeDeselect;
import drawing.command.UpdateModelShapeDeselectAll;
import drawing.command.UpdateModelShapeSelect;
import drawing.command.UpdateShapeProperties;
import drawing.geometry.Circle;
import drawing.geometry.Donut;
import drawing.geometry.Line;
import drawing.geometry.Point;
import drawing.geometry.Rectangle;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import drawing.modals.DlgManageCircle;
import drawing.modals.DlgManageDonut;
import drawing.modals.DlgManageHexagon;
import drawing.modals.DlgManageLine;
import drawing.modals.DlgManagePoint;
import drawing.modals.DlgManageRectangle;
import drawing.mvc.models.CanvasModel;
import drawing.mvc.models.ToolbarModel;
import drawing.mvc.views.CanvasView;
import drawing.mvc.views.MenubarView;
import drawing.mvc.views.ToolbarView;
import drawing.observer.ToolbarPropertyObserver;
import drawing.strategy.FileManager;
import drawing.strategy.RawFileOperator;
import drawing.types.ToolAction;

public class DrawingController {
	private Point startPoint;
	private Point endPoint;

	private Shape createdShape;

	private CanvasModel model;
	private ToolbarModel toolbarModel;

	private CanvasView view;
	private ToolbarView toolbarView;
	private MenubarView menubarView;

	ToolbarPropertyObserver toolbarPropertyObserver;

	private Stack<ICommand> actionStack = new Stack<>();
	private Stack<ICommand> actionStackPopped = new Stack<>();

	private ICommand command;

	public DrawingController(CanvasModel model, ToolbarModel toolbarModel) {
		this.model = model;
		this.toolbarModel = toolbarModel;
	}

	public void setViews(CanvasView view, ToolbarView toolbarView, MenubarView menubarView) {
		this.view = view;
		this.toolbarView = toolbarView;
		this.menubarView = menubarView;

		this.toolbarPropertyObserver = new ToolbarPropertyObserver(this);
		model.addPropertyObserver(this.toolbarPropertyObserver);
	}

	private void clearWorkspace() {
		startPoint = null;
		endPoint = null;
		createdShape = null;

		command = null;
		actionStack.clear();
		actionStackPopped.clear();

		model.removeAllShapes();
		view.repaint();
	}

	private void executeCommand() {
		try {
			command.execute();
			actionStack.push(command);
			actionStackPopped.clear();
			System.out.println(actionStack.size() + ". Command: " + command.getClass());
			command = null;
			view.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.menubarView.setEnabledUndo(!actionStack.isEmpty());
		this.menubarView.setEnabledRedo(!actionStackPopped.isEmpty());
	}

	private void showDialog(JDialog modal) {
		modal.pack();
		modal.setLocationRelativeTo(view);
		modal.setVisible(true);
	}

	private void showAlert(String message) {
		JOptionPane.showMessageDialog(view, message, "Operation Alert", JOptionPane.INFORMATION_MESSAGE);
	}

	private int showConfirm(String message) {
		return JOptionPane.showConfirmDialog(view, message, "Confirm Operation", JOptionPane.YES_NO_OPTION);
	}

	private JFileChooser getJFileChooser(String title) {
		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jFileChooser.setDialogTitle(title);
		jFileChooser.setMultiSelectionEnabled(false);
		jFileChooser.enableInputMethods(false);
		return jFileChooser;
	}

	/*
	 * Canvas commands
	 */

	public void mousePressed(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());

		if (model.getAllSelectedShapeIndexes().size() > 0 && toolbarModel.getToolAction() != ToolAction.SELECT) {
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
		}

		switch (toolbarModel.getToolAction()) {
		case POINT:
			createdShape = mousePoint;
			createdShape.setColor(toolbarModel.getShapeColor());
			createdShape.setSelected(true);
			command = new UpdateModelAddShape(model, createdShape);
			executeCommand();
			break;
		case LINE:
			createdShape = new Line(startPoint);
			createdShape.setColor(toolbarModel.getShapeColor());
			createdShape.setSelected(true);
			break;
		case RECTANGLE:
			createdShape = new Rectangle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case CIRCLE:
			createdShape = new Circle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case DONUT:
			createdShape = new Donut();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case HEXAGON:
			createdShape = new HexagonAdapter();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		default:
		case SELECT:
			Optional<Shape> optionalShape = model.getShapeAt(startPoint);
			if (optionalShape.isEmpty()) {
				if (!model.getAllSelectedShapes().isEmpty()) {
					command = new UpdateModelShapeDeselectAll(model);
					executeCommand();
				}
			} else {

				if (optionalShape.get().isSelected()) {
					if (model.getIsShiftDown()) {
						command = new UpdateModelShapeDeselect(model, optionalShape.get());
						executeCommand();
					}
				} else {
					if (!model.getIsShiftDown() && !model.getAllSelectedShapes().isEmpty()) {
						command = new UpdateModelShapeDeselectAll(model);
						executeCommand();
					}

					command = new UpdateModelShapeSelect(model, optionalShape.get());
					executeCommand();
				}
			}

			for (Shape shape : model.getAllSelectedShapes()) {
				this.setColorPickerShapeColor(shape.getColor());
				if (shape instanceof SurfaceShape) {
					this.setColorPickerShapeBackground(((SurfaceShape) shape).getBackgroundColor());
					view.repaint();
				} else {
					this.setColorPickerShapeBackground(null);
					view.repaint();
				}
			}
			break;
		}
	}

	public void mouseDragged(MouseEvent e) {
		endPoint = new Point(e.getX(), e.getY());

		double distance = startPoint.distanceOf(endPoint);

		switch (toolbarModel.getToolAction()) {
		case SELECT:
			try {
				if (command instanceof UpdateModelSelectedShapesPosition) {
					command.undo();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			command = new UpdateModelSelectedShapesPosition(model, startPoint, endPoint);
			try {
				command.execute();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.repaint();
			break;
		case LINE:
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			if (distance > 10) {
				createdShape.setEndPoint(endPoint);

				if (!model.contains(createdShape)) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			view.repaint();
			break;
		case POINT:
			createdShape.setEndPoint(endPoint);
			view.repaint();
			break;
		default:
			break;
		}

	}

	public void mouseReleased(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		endPoint = mousePoint;

		double distance = startPoint.distanceOf(endPoint);

		boolean initShapeViaDialog = distance < 10;

		switch (toolbarModel.getToolAction()) {
		case SELECT:
			if (command instanceof UpdateModelSelectedShapesPosition) {
				try {
					command.undo();
					executeCommand();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case POINT:
			createdShape.setEndPoint(endPoint);
			break;
		case LINE:
			if (initShapeViaDialog) {
				DlgManageLine modal = new DlgManageLine((Line) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			break;
		case RECTANGLE:
			if (initShapeViaDialog) {
				DlgManageRectangle modal = new DlgManageRectangle((Rectangle) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			break;
		case CIRCLE:
			if (initShapeViaDialog) {
				DlgManageCircle modal = new DlgManageCircle((Circle) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			break;
		case DONUT:
			if (initShapeViaDialog) {
				DlgManageDonut modal = new DlgManageDonut((Donut) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			break;
		case HEXAGON:
			if (initShapeViaDialog) {
				DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();
				}
			}
			break;
		default:
			createdShape = null;
			break;
		}
	}

	/*
	 * Menubar commands
	 */

	public void undo() {
		try {
			command = actionStack.pop();
			command.undo();
			actionStackPopped.push(command);
			System.out.println(actionStack.size() + 1 + ". Undo command: " + command.getClass());
			command = null;
			view.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.menubarView.setEnabledUndo(!actionStack.isEmpty());
		this.menubarView.setEnabledRedo(!actionStackPopped.isEmpty());
	}

	public void redo() {
		try {
			command = actionStackPopped.pop();
			command.redo();
			actionStack.push(command);
			System.out.println(actionStack.size() + ". Redo command: " + command.getClass());
			command = null;
			view.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.menubarView.setEnabledUndo(!actionStack.isEmpty());
		this.menubarView.setEnabledRedo(!actionStackPopped.isEmpty());
	}

	public void moveSelectedForward() {
		command = new UpdateModelSelectedShapesForward(model);
		executeCommand();
	}

	public void moveSelectedBackward() {
		command = new UpdateModelSelectedShapesBackward(model);
		executeCommand();
	}

	public void moveSelectedToFront() {
		command = new UpdateModelSelectedShapesToFront(model);
		executeCommand();
	}

	public void moveSelectedToBack() {
		command = new UpdateModelSelectedShapesToBack(model);
		executeCommand();
	}

	public void duplicateSelected() {
		command = new UpdateModelDuplicateSelectedShape(model);
		this.executeCommand();
	}

	public void startNewWorkspace() {
		int option = this.showConfirm("This will clear your current canvas space, are you sure?");
		if (option == JOptionPane.YES_OPTION) {
			this.clearWorkspace();
		}

	}

	public void saveAsRawFile() {
		JFileChooser jFileChooser = getJFileChooser("Save Raw File Dialog");
		jFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
		int response = jFileChooser.showSaveDialog(view);

		if (response != JFileChooser.APPROVE_OPTION) {
			if (response != JFileChooser.CANCEL_OPTION) {
				showAlert("There was an error while performing saving of raw file");
			}
		}

		try {
			String filepath = jFileChooser.getSelectedFile().getPath();
			FileManager file = new FileManager(new RawFileOperator(model));
			file.saveFile(filepath);
		} catch (Exception ex) {
			showAlert(ex.getMessage());
		}

	}

	public void loadARawFile() {
		JFileChooser jFileChooser = getJFileChooser("Load Raw File Dialog");
		int response = jFileChooser.showOpenDialog(view);

		if (response != JFileChooser.APPROVE_OPTION) {
			if (response != JFileChooser.CANCEL_OPTION) {
				showAlert("There was an error while performing loading of raw file");
			}
		}

		if (jFileChooser.getSelectedFile() == null) {
			showAlert("No raw file selected");
		}

		try {

			String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			FileManager file = new FileManager(new RawFileOperator(model));

			clearWorkspace();
			file.loadFile(filePath);
			view.repaint();
		} catch (Exception ex) {
			showAlert(ex.getMessage());
		}
	}

	/*
	 * Toolbar commands
	 */

	public void setToolAction(ToolAction action) {
		toolbarModel.setToolAction(action);
		switch (toolbarModel.getToolAction()) {
		case POINT:
		case LINE:
			toolbarModel.setShapeColor(toolbarModel.getShapeColor());
			toolbarView.setEnabledPreviewShapeBackgroundColor(false);
			break;
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			toolbarModel.setShapeColor(toolbarModel.getShapeColor());
			toolbarView.setEnabledPreviewShapeBackgroundColor(true);
		default:
			break;
		}

		view.repaint();
	}

	private Color getColorFromColorPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(view, title, initialColor);
		return color;
	}

	public void colorPickerPickBackgroundColor() {
		Color selectedColor = getColorFromColorPicker("Choose Background Color", toolbarModel.getShapeColor());
		if (selectedColor != null) {
			toolbarModel.setShapeBackground(selectedColor);
			toolbarView.setPreviewShapeBackgroundColor(selectedColor);
			if (model.getAllSelectedShapeIndexes().isEmpty()) {
				return;
			}
			command = new UpdateModelSelectedShapesBackgroundColor(model, selectedColor);
			this.executeCommand();
		}
	}

	public void colorPickerPickOutlineColor() {
		Color selectedColor = getColorFromColorPicker("Choose Outline Color", toolbarModel.getShapeBackground());
		if (selectedColor != null) {
			toolbarModel.setShapeColor(selectedColor);
			toolbarView.setPreviewShapeColor(selectedColor);
			if (model.getAllSelectedShapeIndexes().isEmpty()) {
				return;
			}
			command = new UpdateModelSelectedShapesColor(model, selectedColor);
			this.executeCommand();

		}
	}

	public void setColorPickerShapeColor(Color color) {
		toolbarModel.setShapeColor(color);
		toolbarView.setPreviewShapeColor(color);
	}

	public void setColorPickerShapeBackground(Color color) {
		if (color == null) {
			toolbarView.setEnabledPreviewShapeBackgroundColor(false);
			return;
		}
		toolbarModel.setShapeBackground(color);
		toolbarView.setPreviewShapeBackgroundColor(color);
	}

	public void deleteSelected() {
		command = new UpdateModelRemoveSelectedShapes(model);
		this.executeCommand();
	}

	public void modifySelected() {
		Shape selectedShape = model.getAllSelectedShapes().getFirst();
		Shape update = selectedShape.clone();
		if (selectedShape instanceof Point) {
			DlgManagePoint modal = new DlgManagePoint((Point) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}
		if (selectedShape instanceof Line) {
			DlgManageLine modal = new DlgManageLine((Line) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}
		if (selectedShape instanceof Rectangle) {
			DlgManageRectangle modal = new DlgManageRectangle((Rectangle) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}

		if (selectedShape instanceof Donut) {
			DlgManageDonut modal = new DlgManageDonut((Donut) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}

		if (selectedShape instanceof Circle) {
			DlgManageCircle modal = new DlgManageCircle((Circle) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}

		if (selectedShape instanceof HexagonAdapter) {
			DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) update);
			showDialog(modal);
			command = new UpdateShapeProperties(selectedShape, update);
			this.executeCommand();
			return;
		}

	}

	public void setEnabledToolbarDelete(boolean isEnabled) {
		toolbarView.setEnabledDelete(isEnabled);
	}

	public void setEnabledToolbarModify(boolean isEnabled) {
		toolbarView.setEnabledModify(isEnabled);
	}

}
