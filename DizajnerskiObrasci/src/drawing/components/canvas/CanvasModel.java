package drawing.components.canvas;

import java.awt.Color;

import javax.swing.DefaultListModel;

import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;

public class CanvasModel {
	private DefaultListModel<Shape> selectedShapes = new DefaultListModel<>();
	private DefaultListModel<Shape> shapes = new DefaultListModel<>();
	private boolean shiftDown = false;

	public void refreshList() {
		for (int index = shapes.size(); --index >= 0;) {
			shapes.set(index, shapes.get(index));
		}
	}

	public void addShape(Shape shape) {
		shapes.addElement(shape);
	}

	public void removeShape(Shape shape) {
		shapes.removeElement(shape);
	}

	public void removeSelectedShapes() {
		for (int index = selectedShapes.size(); --index >= 0;) {
			shapes.removeElement(selectedShapes.get(index));
		}
		selectedShapes.clear();
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	public DefaultListModel<Shape> getAllShapes() {
		return shapes;
	}

	public DefaultListModel<Shape> getAllSelectedShapes() {
		return selectedShapes;
	}

	public void selectShapeAt(Point point) {
		int index;

		int initialSelectedShapesSize = selectedShapes.size();

		for (index = shapes.size(); --index >= 0;) {
			if (shapes.get(index).contains(point)) {
				if (!shapes.get(index).isSelected()) {
					shapes.get(index).setSelected(true);
					selectedShapes.addElement(shapes.get(index));
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
		selectedShapes.addElement(shape);

	}

	public void deselectShape(Shape leave) {
		for (int index = shapes.size(); --index >= 0;) {
			if (!shapes.get(index).equals(leave)) {
				shapes.get(index).setSelected(false);
				selectedShapes.removeElement(shapes.get(index));
			}
		}
	}

	public void deselectAllShapes() {
		for (int index = selectedShapes.size(); --index >= 0;) {
			selectedShapes.get(index).setSelected(false);
			selectedShapes.remove(index);
		}
	}

	public void moveSelectedShapesBy(double x, double y) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			selectedShapes.get(index).moveBy((int) x, (int) y);
		}
	}

	public void UpdateColorOfSelectedShapes(Color color) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			selectedShapes.get(index).setColor(color);

		}
	}

	public void UpdateBackgroundColorOfSelectedShapes(Color color) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			if (selectedShapes.get(index) instanceof SurfaceShape) {
				((SurfaceShape) selectedShapes.get(index)).setBackgroundColor(color);
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
