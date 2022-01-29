package it68_2019.dp.drawing.controllers;

import java.awt.event.MouseEvent;

import it68_2019.dp.drawing.models.DrawingModel;
import it68_2019.dp.drawing.models.geometry.Line;
import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.models.geometry.Shape;
import it68_2019.dp.drawing.views.DrawingFrame;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape createdShape;
	private Point startPoint;
	private Point endPoint;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		createdShape = new Point(e.getX(), e.getY());
		model.add(createdShape);
		createdShape = null;
		frame.repaint();
	}

	public void mousePressed(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		endPoint = new Point(e.getX(), e.getY());
		createdShape = new Line(startPoint, endPoint);
		model.add(createdShape);
	}

	public void mouseReleased(MouseEvent e) {
		frame.repaint();
		createdShape = null;
	}

	public void mouseDragged(MouseEvent e) {
		if(createdShape != null) {
			endPoint.setX(e.getX());
			endPoint.setY(e.getY());
			frame.repaint();
		}
		
		
	}
}
