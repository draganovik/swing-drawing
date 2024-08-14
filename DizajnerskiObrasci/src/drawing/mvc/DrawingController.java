package drawing.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

import drawing.adapters.HexagonAdapter;
import drawing.command.ICommand;
import drawing.command.UpdateModelAddShape;
import drawing.command.UpdateModelSelectedShapesBackward;
import drawing.command.UpdateModelSelectedShapesForward;
import drawing.command.UpdateModelSelectedShapesPosition;
import drawing.command.UpdateModelSelectedShapesToBack;
import drawing.command.UpdateModelSelectedShapesToFront;
import drawing.command.UpdateModelShapeDeselect;
import drawing.command.UpdateModelShapeDeselectAll;
import drawing.command.UpdateModelShapeSelect;
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
import drawing.mvc.views.ToolbarView;
import drawing.observer.ToolbarPropertyObserver;
import drawing.types.ToolAction;

public class DrawingController {
	private Point startPoint;
	private Point endPoint;

	private Shape createdShape;

	private CanvasModel model;
	private ToolbarModel toolbarModel;

	private CanvasView view;
	private ToolbarView toolbarView;

	ToolbarPropertyObserver toolbarPropertyObserver;

	private Stack<ICommand> actionStack = new Stack<>();
	private Stack<ICommand> actionStackPopped = new Stack<>();

	private ICommand command;

	public DrawingController(CanvasModel model, ToolbarModel toolbarModel) {
		this.model = model;
		this.toolbarModel = toolbarModel;
	}

	public void setViews(CanvasView view, ToolbarView toolbarView) {
		this.view = view;
		this.toolbarView = toolbarView;

		this.toolbarPropertyObserver = new ToolbarPropertyObserver(toolbarView);
		model.addPropertyObserver(this.toolbarPropertyObserver);
	}

	private void executeCommand() {
		command.execute();
		actionStack.push(command);
		actionStackPopped.clear();
		command = null;
		view.repaint();
	}

	private void showDialog(JDialog modal) {
		modal.pack();
		modal.setLocationRelativeTo(view);
		modal.setVisible(true);
	}

	/*
	 * Canvas commands
	 */

	public void mousePressed(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());

		switch (toolbarModel.getToolAction()) {
		case POINT:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = mousePoint;
			createdShape.setColor(toolbarModel.getShapeColor());
			createdShape.setSelected(true);
			break;
		case LINE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Line(startPoint);
			createdShape.setColor(toolbarModel.getShapeColor());
			createdShape.setSelected(true);
			break;
		case RECTANGLE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Rectangle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case CIRCLE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Circle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case DONUT:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Donut();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			createdShape.setSelected(true);
			break;
		case HEXAGON:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
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
					this.setPickerShapeBackground(((SurfaceShape) shape).getBackgroundColor());
					view.repaint();
				} else {
					this.setPickerShapeBackground(null);
					view.repaint();
				}
			}
			break;
		}
	}

	public void mouseDragged(MouseEvent e) {

		endPoint = new Point(e.getX(), e.getY());
		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);

		switch (toolbarModel.getToolAction()) {
		case SELECT:
			if (command instanceof UpdateModelSelectedShapesPosition) {
				command.undo();
			}
			command = new UpdateModelSelectedShapesPosition(model, startPoint, endPoint);
			command.execute();
			view.repaint();
			break;
		case POINT:
		case LINE:
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			if (pointsXDistance > 8 && pointsYDistance > 8) {
				createdShape.setEndPoint(endPoint);

				if (!model.contains(createdShape)) {
					command = new UpdateModelAddShape(model, createdShape);
					executeCommand();

				}
			}
			view.repaint();
		default:
			break;
		}

	}

	public void mouseReleased(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		endPoint = mousePoint;

		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);
		boolean initShapeViaDialog = pointsXDistance < 8 || pointsYDistance < 8;

		switch (toolbarModel.getToolAction()) {
		case SELECT:
			if (command instanceof UpdateModelSelectedShapesPosition) {
				command.undo();
				executeCommand();
			}
			break;
		case POINT:
			if (createdShape instanceof Point) {
				createdShape.setEndPoint(endPoint);
				command = new UpdateModelAddShape(model, createdShape);
				executeCommand();

			}
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

					view.repaint();
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

					view.repaint();
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
		if (actionStack.isEmpty()) {
			return;
		}
		command = actionStack.pop();
		command.undo();
		actionStackPopped.push(command);
		command = null;
		view.repaint();
	}

	public void redo() {
		if (actionStackPopped.isEmpty()) {
			return;
		}
		command = actionStackPopped.pop();
		command.redo();
		actionStack.push(command);
		command = null;
		view.repaint();
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
		model.duplicateSelected();
		view.repaint();

	}

	/*
	 * Toolbar commands
	 */

	public void setToolAction(ToolAction action) {
		toolbarModel.setToolAction(action);
		switch (toolbarModel.getToolAction()) {
		case POINT:
		case LINE:
			// canvasModel.deselectAllShapes();
			toolbarView.btnToolbarColor
					.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, toolbarModel.getShapeColor()));
			toolbarView.btnToolbarBackground.setBackground(null);
			toolbarView.btnToolbarBackground.setEnabled(false);
			break;
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			// canvasModel.deselectAllShapes();
			toolbarView.btnToolbarColor
					.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, toolbarModel.getShapeColor()));
			toolbarView.btnToolbarBackground.setBackground(toolbarModel.getShapeBackground());
			toolbarView.btnToolbarBackground.setEnabled(true);
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
			model.updateBackgroundColorOfSelectedShapes(selectedColor);
			toolbarView.btnToolbarBackground.setBackground(toolbarModel.getShapeBackground());
			view.repaint();
		}
	}

	public void colorPickerPickOutlineColor() {
		Color selectedColor = getColorFromColorPicker("Choose Outline Color", toolbarModel.getShapeBackground());
		if (selectedColor != null) {
			toolbarModel.setShapeColor(selectedColor);
			model.updateColorOfSelectedShapes(selectedColor);
			toolbarView.btnToolbarColor
					.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, toolbarModel.getShapeColor()));
			view.repaint();
		}
	}

	public void setColorPickerShapeColor(Color color) {
		toolbarModel.setShapeColor(color);
		toolbarView.btnToolbarColor
				.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, toolbarModel.getShapeColor()));
	}

	public void setPickerShapeBackground(Color color) {
		toolbarModel.setShapeBackground(color);
		toolbarView.btnToolbarBackground.setBackground(toolbarModel.getShapeBackground());
	}

	public void deleteSelected() {
		model.removeSelectedShapes();
		view.repaint();
	}

	public void modifySelected() {
		Shape selectedShape = model.getAllSelectedShapes().get(0);
		if (selectedShape instanceof Point) {
			DlgManagePoint modal = new DlgManagePoint((Point) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}
		if (selectedShape instanceof Line) {
			DlgManageLine modal = new DlgManageLine((Line) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}
		if (selectedShape instanceof Rectangle) {
			DlgManageRectangle modal = new DlgManageRectangle((Rectangle) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}

		if (selectedShape instanceof Donut) {
			DlgManageDonut modal = new DlgManageDonut((Donut) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}

		if (selectedShape instanceof Circle) {
			DlgManageCircle modal = new DlgManageCircle((Circle) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}

		if (selectedShape instanceof HexagonAdapter) {
			DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) model.getAllSelectedShapes().get(0));
			showDialog(modal);
			view.repaint();
			return;
		}
	}

}
