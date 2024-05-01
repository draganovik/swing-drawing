package drawing.components.canvas;

import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JDialog;

import drawing.adapters.HexagonAdapter;
import drawing.components.toolbar.ToolbarController;
import drawing.components.toolbar.ToolbarModel;
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

public class CanvasController {
	private Shape createdShape;
	private Point endPoint;
	private CanvasModel model;
	private Point startPoint;
	private ToolbarModel toolModel;
	private ToolbarController toolbarController;
	private CanvasView view;

	public CanvasController(CanvasModel model, ToolbarModel toolModel, ToolbarController toolbarController,
			CanvasView view) {
		this.model = model;
		this.toolModel = toolModel;
		this.view = view;
		this.toolbarController = toolbarController;
	}

	public void mouseDragged(MouseEvent e) {

		endPoint = new Point(e.getX(), e.getY());
		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);

		switch (toolModel.getToolAction()) {
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
					model.addShape(createdShape);
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
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());

		switch (toolModel.getToolAction()) {
		case POINT:
			model.deselectAllShapes();
			createdShape = new Point();
			createdShape.setColor(toolModel.getShapeColor());
			createdShape.setStartPoint(startPoint);
			model.addShape(createdShape);
			model.selectShape(createdShape);
			break;
		case LINE:
			model.deselectAllShapes();
			createdShape = new Line(startPoint);
			createdShape.setColor(toolModel.getShapeColor());
			break;
		case RECTANGLE:
			model.deselectAllShapes();
			createdShape = new Rectangle();
			createdShape.setColor(toolModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case CIRCLE:
			model.deselectAllShapes();
			createdShape = new Circle();
			createdShape.setColor(toolModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case DONUT:
			model.deselectAllShapes();
			createdShape = new Donut();
			createdShape.setColor(toolModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		case HEXAGON:
			model.deselectAllShapes();
			createdShape = new HexagonAdapter();
			createdShape.setColor(toolModel.getShapeColor());
			((SurfaceShape) createdShape).setBackgroundColor(toolModel.getShapeBackground());
			createdShape.setStartPoint(startPoint);
			break;
		default:
		case SELECT:
			model.selectShapeAt(mousePoint);
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

		model.refreshList();
		view.repaint();
	}

	public void mouseReleased(MouseEvent e) {

		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);
		Boolean initShapeViaDialog = pointsXDistance < 8 || pointsYDistance < 8;

		switch (toolModel.getToolAction()) {
		case POINT:
			break;
		case LINE:
			if (initShapeViaDialog) {
				DlgManageLine modal = new DlgManageLine((Line) createdShape);
				showDialog(modal);
				if (modal.IsSuccessful) {
					model.addShape(createdShape);
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
					model.addShape(createdShape);
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
					model.addShape(createdShape);
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
					model.addShape(createdShape);
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
					model.addShape(createdShape);
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
}
