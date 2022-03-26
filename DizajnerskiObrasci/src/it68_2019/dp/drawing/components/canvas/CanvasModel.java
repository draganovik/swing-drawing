package it68_2019.dp.drawing.components.canvas;

import java.util.ArrayList;

import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.models.geometry.Shape;

public class CanvasModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private boolean shiftDown = false;

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
	public void select(Point point) {
		if (!shiftDown) {
		for (Shape shape : shapes) {
			shape.setSelected(false);
			selectedShapes.remove(shape);
		}
		}
		for (int i = shapes.size(); i-- > 0;) {
			if (shapes.get(i).contains(point)) {
				shapes.get(i).setSelected(true);
				selectedShapes.add(shapes.get(i));
				break;
			}
		}
	}

	public ArrayList<Shape> getAllShapes() {
		return shapes;
	}

	public void isShiftDown(boolean shiftDown) {
		this.shiftDown = shiftDown;
		
	}

}
