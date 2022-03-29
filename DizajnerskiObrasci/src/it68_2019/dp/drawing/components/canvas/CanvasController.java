package it68_2019.dp.drawing.components.canvas;

import java.awt.event.MouseEvent;

import it68_2019.dp.drawing.adapters.HexagonAdapter;
import it68_2019.dp.drawing.models.geometry.Circle;
import it68_2019.dp.drawing.models.geometry.Donut;
import it68_2019.dp.drawing.models.geometry.Line;
import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.models.geometry.Rectangle;
import it68_2019.dp.drawing.models.geometry.Shape;
import it68_2019.dp.drawing.types.ToolbarAction;

public class CanvasController {
	private CanvasModel model;
	private CanvasView view;
	private Shape createdShape;
	private Point startPoint;
	private Point endPoint;

	public CanvasController(CanvasModel model, CanvasView view) {
		this.model = model;
		this.view = view;
	}

	public void createShape(ToolbarAction toolbarAction) {
		switch (toolbarAction) {
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
		createdShape = null;
	}

	public void mouseDragged(MouseEvent e) {
		if (createdShape != null) {
			endPoint = new Point(e.getX(), e.getY());
			createdShape.setEndPoint(endPoint);
			view.repaint();
		}

	}
}
