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
					break;
				}
				if (shiftDown) {
					shapes.get(index).setSelected(false);
					selectedShapes.removeElement(shapes.get(index));
					break;
				}
				break;
			}
		}

		if (!shiftDown && selectedShapes.size() > 0) {
			if (initialSelectedShapesSize < selectedShapes.size()) {
				this.deselectAllShapes(selectedShapes.lastElement());
			}
		}
		if (index == -1) {
			this.deselectAllShapes();
		}
	}

	public void selectShape(Shape shape) {
		shape.setSelected(true);
		selectedShapes.addElement(shape);

	}

	public void deselectShape(Shape shape) {
		shape.setSelected(false);
		selectedShapes.removeElement(shape);

	}

	public void deselectAllShapes(Shape leave) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			if (!selectedShapes.get(index).equals(leave)) {
				selectedShapes.get(index).setSelected(false);
				selectedShapes.removeElement(selectedShapes.get(index));
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

	public void updateColorOfSelectedShapes(Color color) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			selectedShapes.get(index).setColor(color);

		}
	}

	public void updateBackgroundColorOfSelectedShapes(Color color) {
		for (int index = selectedShapes.size(); --index >= 0;) {
			if (selectedShapes.get(index) instanceof SurfaceShape) {
				((SurfaceShape) selectedShapes.get(index)).setBackgroundColor(color);
			}
		}
	}

	private void moveShapeForward(Shape shape) {
		if (shapes.lastElement() != shape && shape != null) {
			int index = shapes.indexOf(shape);
			Shape switchShape = shapes.getElementAt(index + 1);

			shapes.set(index, switchShape);
			shapes.set(index + 1, shape);

		}
	}

	private void moveShapeBackward(Shape shape) {
		if (shapes.firstElement() != shape && shape != null) {
			int index = shapes.indexOf(shape);
			Shape switchShape = shapes.getElementAt(index - 1);

			shapes.set(index, switchShape);
			shapes.set(index - 1, shape);

		}
	}

	private void moveShapeToFront(Shape shape) {
		if (shapes.lastElement() != shape) {
			shapes.remove(shapes.indexOf(shape));
			shapes.insertElementAt(shape, shapes.size());

		}
	}

	private void moveShapeToBack(Shape shape) {
		if (shapes.firstElement() != shape) {
			shapes.remove(shapes.indexOf(shape));
			shapes.insertElementAt(shape, 0);

		}
	}

	public void moveSelectedShapesBackward() {
		for (int index = selectedShapes.size(); --index >= 0;) {
			this.moveShapeBackward(selectedShapes.get(index));
		}
	}

	public void moveSelectedShapesForward() {
		for (int index = 0; index < selectedShapes.size(); index++) {
			this.moveShapeForward(selectedShapes.get(index));
		}
	}

	public void moveSelectedShapesToBack() {
		for (int index = selectedShapes.size(); --index >= 0;) {
			this.moveShapeToBack(selectedShapes.get(index));
		}
	}

	public void moveSelectedShapesToFront() {
		for (int index = 0; index < selectedShapes.size(); index++) {
			this.moveShapeToFront(selectedShapes.get(index));
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

	public void duplicateSelected() {
		int initSelectSize = selectedShapes.size();
		for (int index = 0; index < initSelectSize; index++) {
			Shape clone = selectedShapes.firstElement().clone();
			this.deselectShape(selectedShapes.firstElement());
			this.addShape(clone);
			this.selectShape(clone);
		}

	}

}
