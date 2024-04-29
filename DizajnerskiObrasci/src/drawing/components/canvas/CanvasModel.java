package drawing.components.canvas;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;

public class CanvasModel {
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	private ArrayList<Shape> shapes = new ArrayList<>();
	private boolean shiftDown = false;

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public void removeSelectedShapes() {
		for (Shape shape : selectedShapes) {
			shapes.remove(shape);
		}
		selectedShapes.clear();
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	public ArrayList<Shape> getAllShapes() {
		return shapes;
	}

	public ArrayList<Shape> getAllSelectedShapes() {
		return selectedShapes;
	}

	public void selectShapeAt(Point point) {
		int index;

		int initialSelectedShapesSize = selectedShapes.size();

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
			if (initialSelectedShapesSize != selectedShapes.size()) {
				this.deselectShape(selectedShapes.get(selectedShapes.size() - 1));
			}
			if (selectedShapes.size() > 0 && index == -1) {
				this.deselectAllShapes();
			}
		}
	}

	public void selectShape(Shape shape) {
		shape.setSelected(true);
		selectedShapes.add(shape);

	}

	public void deselectShape(Shape leave) {
		for (Shape shape : shapes) {
			if (!shape.equals(leave)) {
				shape.setSelected(false);
				selectedShapes.remove(shape);
			}
		}
	}

	public void deselectAllShapes() {
		for (Shape shape : shapes) {
			shape.setSelected(false);
			selectedShapes.remove(shape);
		}
	}

	public void moveSelectedShapesBy(double x, double y) {
		for (Shape shape : selectedShapes) {
			shape.moveBy((int) x, (int) y);
		}
	}

	public void UpdateColorOfSelectedShapes(Color color) {
		for (Shape shape : selectedShapes) {
			shape.setColor(color);

		}
	}

	public void UpdateBackgroundColorOfSelectedShapes(Color color) {
		for (Shape shape : selectedShapes) {
			if (shape instanceof SurfaceShape) {
				((SurfaceShape) shape).setBackgroundColor(color);
			}
		}
	}

	public void setIsShiftDown(boolean shiftDown) {
		this.shiftDown = shiftDown;

	}

	public Boolean getIsShiftDown() {
		return this.shiftDown;
	}

	public Boolean contains(Shape shape) {
		return shapes.contains(shape);
	}

}
