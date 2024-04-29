package drawing.components.canvas;

import java.awt.event.MouseEvent;
import java.util.Enumeration;

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

	public void handleAction() {
		switch (toolModel.getToolAction()) {
		case POINT:
			model.deselectAllShapes();
			createdShape = new Point();
			break;
		case LINE:
			model.deselectAllShapes();
			createdShape = new Line();
			break;
		case RECTANGLE:
			model.deselectAllShapes();
			createdShape = new Rectangle();
			break;
		case CIRCLE:
			model.deselectAllShapes();
			createdShape = new Circle();
			break;
		case DONUT:
			model.deselectAllShapes();
			createdShape = new Donut();
			break;
		case HEXAGON:
			model.deselectAllShapes();
			createdShape = new HexagonAdapter();
			break;
		default:
		case SELECT:
			createdShape = null;
			break;
		}

		if (createdShape != null) {
			createdShape.setColor(toolModel.getShapeColor());
			if (createdShape instanceof SurfaceShape) {
				((SurfaceShape) createdShape).setBackgroundColor(toolModel.getShapeBackground());
			}
		}

		model.refreshList();
		view.repaint();
	}

	public void mouseDragged(MouseEvent e) {

		endPoint = new Point(e.getX(), e.getY());

		if (createdShape == null) {
			model.moveSelectedShapesBy(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
			model.refreshList();
			view.repaint();
			startPoint = endPoint;
			return;
		}

		double pointsXDistance = startPoint.distanceByXOf(endPoint);
		double pointsYDistance = startPoint.distanceByYOf(endPoint);

		if (createdShape instanceof SurfaceShape && (pointsXDistance < 8 || pointsYDistance < 8)) {
			return;
		}

		createdShape.setEndPoint(endPoint);

		if (!model.contains(createdShape)) {
			model.addShape(createdShape);
		}
		model.refreshList();
		view.repaint();

	}

	public void mousePressed(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		startPoint = mousePoint;
		endPoint = mousePoint;

		model.setIsShiftDown(e.isShiftDown());
		model.selectShapeAt(mousePoint);

		handleAction();

		switch (toolModel.getToolAction()) {
		case SELECT:
			for (Enumeration<Shape> en = model.getAllSelectedShapes().elements(); en.hasMoreElements();) {
				Shape shape = en.nextElement();
				toolbarController.setShapeColor(shape.getColor());
				if (shape instanceof SurfaceShape) {
					toolbarController.setShapeBackground(((SurfaceShape) shape).getBackgroundColor());
				} else {
					toolbarController.setShapeBackground(null);
				}
			}
			return;
		default:
			break;
		}

		createdShape.setStartPoint(startPoint);
		createdShape.setEndPoint(endPoint);

		if (!(createdShape instanceof SurfaceShape)) {
			model.addShape(createdShape);
		}
	}

	public void mouseReleased(MouseEvent e) {
		model.refreshList();
		view.repaint();
		if (createdShape != null) {
			model.selectShape(createdShape);
			createdShape = null;
		}
	}
}
