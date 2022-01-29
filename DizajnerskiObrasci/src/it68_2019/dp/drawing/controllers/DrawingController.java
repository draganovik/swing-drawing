package it68_2019.dp.drawing.controllers;

import java.awt.event.MouseEvent;

import it68_2019.dp.drawing.models.DrawingModel;
import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.views.DrawingFrame;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		model.add(p);
		frame.repaint();
	}
}
