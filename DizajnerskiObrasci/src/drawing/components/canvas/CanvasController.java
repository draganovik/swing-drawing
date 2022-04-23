package drawing.components.canvas;

import java.awt.event.MouseEvent;

import drawing.adapters.HexagonAdapter;
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
	private CanvasView view;

	public CanvasController(CanvasModel model, ToolbarModel toolModel, CanvasView view) {
		this.model = model;
		this.toolModel = toolModel;
		this.view = view;
	}

	public void createShape() {
		model.unselectAll();
		view.repaint();
		switch (toolModel.getToolAction()) {
		case POINT:
			createdShape = new Point();
			break;
		case LINE:
			createdShape = new Line();
			break;
		case RECTANGLE:
			createdShape = new Rectangle();
			break;
		case CIRCLE:
			createdShape = new Circle();
			break;
		case DONUT:
			createdShape = new Donut();
			break;
		case HEXAGON:
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
	}

	public void mouseDragged(MouseEvent e) {
		if (createdShape != null) {
			endPoint = new Point(e.getX(), e.getY());
			createdShape.setEndPoint(endPoint);
			view.repaint();
		}

	}

	public void mousePressed(MouseEvent e) {
		Point mousePoint = new Point(e.getX(), e.getY());
		if (createdShape != null) {
			startPoint = mousePoint;
			endPoint = mousePoint;
			createdShape.setStartPoint(startPoint);
			createdShape.setEndPoint(endPoint);
			model.add(createdShape);
			return;
		}
		model.isShiftDown(e.isShiftDown());
		model.select(mousePoint);
	}

	public void mouseReleased(MouseEvent e) {
		view.repaint();
		if (createdShape != null) {
			model.select(createdShape);
			createdShape = null;
		}
	}
}
