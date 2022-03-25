package it68_2019.dp.drawing.components.canvas;

import java.awt.event.MouseEvent;

import it68_2019.dp.drawing.models.geometry.*;
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
		 switch(toolbarAction) {
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
			 //createdShape = new Hexagon();
			 break;
		 default:
		 case SELECT:
			 createdShape = null;
			 break;
		 }
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		endPoint = new Point(e.getX(), e.getY());
		createdShape.setStartPoint(startPoint);
		createdShape.setEndPoint(endPoint);
		model.add(createdShape);
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
