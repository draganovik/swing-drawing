package drawing.components.canvas;

import java.util.ArrayList;

import drawing.geometry.Point;
import drawing.geometry.Shape;

public class CanvasModel {
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	private ArrayList<Shape> shapes = new ArrayList<>();
	private boolean shiftDown = false;

	public void add(Shape shape) {
		shapes.add(shape);
	}

	public ArrayList<Shape> getAllShapes() {
		return shapes;
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	public void isShiftDown(boolean shiftDown) {
		this.shiftDown = shiftDown;

	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public void removeSelected() {
		for (Shape shape : selectedShapes) {
			shapes.remove(shape);
		}
		selectedShapes.clear();
	}

	public void select(Point point) {
		int index;
		int selectedShapesInitialSize = selectedShapes.size();
		for (index = shapes.size(); --index >= 0;) {
			if (shapes.get(index).contains(point)) {
				if (!shapes.get(index).isSelected()) {
					shapes.get(index).setSelected(true);
					selectedShapes.add(shapes.get(index));
				}
				break;
			}
		}
		if (!shiftDown) {
			if (selectedShapesInitialSize != selectedShapes.size()) {
				this.unselectAll(selectedShapes.get(selectedShapes.size() - 1));
			}
			if (selectedShapes.size() > 0 && index == -1) {
				this.unselectAll();
			}
		}
	}

	public void unselectAll() {
		for (Shape shape : shapes) {
			shape.setSelected(false);
			selectedShapes.remove(shape);
		}
	}

	public void unselectAll(Shape leave) {
		for (Shape shape : shapes) {
			if (!shape.equals(leave)) {
				shape.setSelected(false);
				selectedShapes.remove(shape);
			}
		}
	}

	public void select(Shape shape) {
		shape.setSelected(true);
		selectedShapes.add(shape);

	}

}
