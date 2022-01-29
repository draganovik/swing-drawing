package it68_2019.dp.drawing.models;

import java.util.ArrayList;

import it68_2019.dp.drawing.models.geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	public ArrayList<Shape> getAllShapes() {
		return shapes;
	}

}
