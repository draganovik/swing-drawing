package drawing.mvc;

import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Stack;

import javax.swing.JDialog;

import drawing.adapters.HexagonAdapter;
import drawing.command.ICommand;
import drawing.command.UpdateModelAddShape;
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

public class CanvasController {
	private Shape createdShape;
	private Point endPoint;
	private CanvasModel model;
	private Point startPoint;
	private ToolbarModel toolbarModel;
	private ToolbarController toolbarController;
	private CanvasView view;
	private Stack<ICommand> actionStack = new Stack<>();
	private Stack<ICommand> actionStackPopped = new Stack<>();

	public CanvasController(CanvasModel model) {
		this.model = model;
	}

	public void setCanvasView(CanvasView view) {
		this.view = view;
	}

	public void setToolbarModelController(ToolbarModel toolbarModel, ToolbarController toolbarController) {
		this.toolbarModel = toolbarModel;
		this.toolbarController = toolbarController;
	}

	public void mouseDragged(MouseEvent e) {

		ICommand command;
		endPoint = new Point(e.getX(), e.getY());
		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);

		switch (toolbarModel.getToolAction()) {
		case SELECT:
			model.moveSelectedShapesBy(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
			startPoint = endPoint;
			break;
		case LINE:
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			if (pointsXDistance > 8 && pointsYDistance > 8) {
				createdShape.setEndPoint(endPoint);

				if (!model.contains(createdShape)) {
					// model.addShape(createdShape);
					command = new UpdateModelAddShape(model, createdShape);
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
				}
			}
		default:
			break;
		}

		model.refreshList();
		view.repaint();

	}

	public void mousePressed(MouseEvent e) {
		ICommand command;
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());

		switch (toolbarModel.getToolAction()) {
		case POINT:
			model.deselectAllShapes();
			createdShape = new Point();
			createdShape.setColor(toolbarModel.getShapeColor());
			createdShape.setStartPoint(startPoint);
			// model.addShape(createdShape);
			command = new UpdateModelAddShape(model, createdShape);
			command.execute();
			actionStack.push(command);

			model.selectShape(createdShape);
			break;
		case LINE:
			model.deselectAllShapes();
			createdShape = new Line(startPoint);
			createdShape.setColor(toolbarModel.getShapeColor());
			break;
		case RECTANGLE:
			model.deselectAllShapes();
			createdShape = new Rectangle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case CIRCLE:
			model.deselectAllShapes();
			createdShape = new Circle();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case DONUT:
			model.deselectAllShapes();
			createdShape = new Donut();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case HEXAGON:
			model.deselectAllShapes();
			createdShape = new HexagonAdapter();
			createdShape.setColor(toolbarModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolbarModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		default:
		case SELECT:
			Optional<Shape> optionalShape = model.getShapeAt(mousePoint);
			if (!optionalShape.isPresent()) {
				if (model.getAllSelectedShapes().size() == 0) {
					break;
				}
				command = new UpdateModelShapeDeselectAll(model);
				command.execute();
				actionStack.push(command);
				break;
			}
			if (optionalShape.get().isSelected() && model.getIsShiftDown()) {
				command = new UpdateModelShapeDeselect(model, optionalShape.get());
				command.execute();
				actionStack.push(command);
			}
			if (!optionalShape.get().isSelected()) {
				if (!model.getIsShiftDown()) {
					command = new UpdateModelShapeDeselectAll(model);
					command.execute();
					actionStack.push(command);
				}
				command = new UpdateModelShapeSelect(model, optionalShape.get());
				command.execute();
				actionStack.push(command);
			}

			for (Enumeration<Shape> en = model.getAllSelectedShapes().elements(); en.hasMoreElements();) {
				Shape shape = en.nextElement();
				toolbarController.setShapeColor(shape.getColor());
				if (shape instanceof SurfaceShape) {
					toolbarController.setShapeBackground(((SurfaceShape) shape).getBackgroundColor());
				} else {
					toolbarController.setShapeBackground(null);
				}
			}
			createdShape = null;
			break;
		}
		view.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		ICommand command;
		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);
		boolean initShapeViaDialog = pointsXDistance < 8 || pointsYDistance < 8;

		switch (toolbarModel.getToolAction()) {
		case POINT:
			break;
		case LINE:
			if (initShapeViaDialog) {
				DlgManageLine modal = new DlgManageLine((Line) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
					model.refreshList();
					view.repaint();
				}
			}
			break;
		case RECTANGLE:
			if (initShapeViaDialog) {
				DlgManageRectangle modal = new DlgManageRectangle((Rectangle) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
					model.refreshList();
					view.repaint();
				}
			}
			break;
		case CIRCLE:
			if (initShapeViaDialog) {
				DlgManageCircle modal = new DlgManageCircle((Circle) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
					model.refreshList();
					view.repaint();
				}
			}
			break;
		case DONUT:
			if (initShapeViaDialog) {
				DlgManageDonut modal = new DlgManageDonut((Donut) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					command = new UpdateModelAddShape(model, createdShape);
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
					model.refreshList();
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
					command.execute();
					actionStack.push(command);

					model.selectShape(createdShape);
					model.refreshList();
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
		ICommand command = actionStack.pop();
		command.undo();
		actionStackPopped.push(command);
		view.repaint();
	}

	public void redo() {
		if (actionStackPopped.isEmpty()) {
			return;
		}
		ICommand command = actionStackPopped.pop();
		command.redo();
		actionStack.push(command);
		view.repaint();
	}
}
