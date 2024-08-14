package drawing.mvc;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.Stack;

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
import drawing.modals.DlgManageRectangle;
import drawing.mvc.views.CanvasView;

public class DrawingController {
	private Shape createdShape;
	private Point endPoint;
	private CanvasModel model;
	private Point startPoint;
	private ToolbarModel toolbarModel;
	private ToolbarController toolbarController;
	private CanvasView view;
	private Stack<ICommand> actionStack = new Stack<>();
	private Stack<ICommand> actionStackPopped = new Stack<>();
	private ICommand command;

	public DrawingController(CanvasModel model) {
		this.model = model;
	}

	public void setCanvasView(CanvasView view) {
		this.view = view;
	}

	public void setToolbarModelController(ToolbarModel toolbarModel, ToolbarController toolbarController) {
		this.toolbarModel = toolbarModel;
		this.toolbarController = toolbarController;
	}

	private void executeCommand() {
		command.execute();
		actionStack.push(command);
		actionStackPopped.clear();
		System.out.println(actionStack.size() + ". executed " + command.getClass());
		command = null;
		view.repaint();
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

					model.selectShape(createdShape);
				}
			}
			view.repaint();
		default:
			break;
		}

	}

	public void mousePressed(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());

		switch (toolbarModel.getToolAction()) {
		case POINT:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Point();
			createdShape.setColor(toolbarModel.getShapeColor());
			break;
		case LINE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Line(startPoint);
			createdShape.setColor(toolbarModel.getShapeColor());
			break;
		case RECTANGLE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Rectangle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case CIRCLE:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Circle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case DONUT:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new Donut();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case HEXAGON:
			command = new UpdateModelShapeDeselectAll(model);
			executeCommand();
			createdShape = new HexagonAdapter();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
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
				toolbarController.setShapeColor(shape.getColor());
				if (shape instanceof SurfaceShape) {
					toolbarController.setShapeBackground(((SurfaceShape) shape).getBackgroundColor());
					view.repaint();
				} else {
					toolbarController.setShapeBackground(null);
					view.repaint();
				}
			}
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
				model.selectShape(createdShape);
				executeCommand();

			}
			break;
		case LINE:
			if (initShapeViaDialog) {
				DlgManageLine modal = new DlgManageLine((Line) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					model.selectShape(createdShape);
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
					model.selectShape(createdShape);
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
					model.selectShape(createdShape);
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

					model.selectShape(createdShape);
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

					model.selectShape(createdShape);
					view.repaint();
				}
			}
			break;
		default:
			createdShape = null;
			break;
		}
	}

	private void showDialog(JDialog modal) {
		modal.pack();
		modal.setLocationRelativeTo(view);
		modal.setVisible(true);
	}

	public void undo() {
		if (actionStack.isEmpty()) {
			return;
		}
		command = actionStack.pop();
		command.undo();
		actionStackPopped.push(command);
		view.repaint();
	}

	public void redo() {
		if (actionStackPopped.isEmpty()) {
			return;
		}
		command = actionStackPopped.pop();
		command.redo();
		actionStack.push(command);
		view.repaint();
	}

	public void moveSelectionForward() {
		command = new UpdateModelSelectedShapesForward(model);
		executeCommand();
	}

	public void moveSelectionBackward() {
		command = new UpdateModelSelectedShapesBackward(model);
		executeCommand();
	}

	public void moveSelectionToFront() {
		command = new UpdateModelSelectedShapesToFront(model);
		executeCommand();
	}

	public void moveSelectionToBack() {
		command = new UpdateModelSelectedShapesToBack(model);
		executeCommand();
	}
}
