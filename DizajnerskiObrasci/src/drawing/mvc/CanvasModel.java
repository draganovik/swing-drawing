package drawing.mvc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.swing.DefaultListModel;

import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;

public class CanvasModel {
	private DefaultListModel<Shape> shapes = new DefaultListModel<>();
	private boolean shiftDown = false;

	public void addShape(Shape shape) {
		shapes.addElement(shape);
	}

	public void removeShape(Shape shape) {
		shapes.removeElement(shape);
	}

	public void insertShape(Shape shape, int index) {
		shapes.insertElementAt(shape, index);
	}

	private ArrayList<Integer> selectedShapesIndexes() {
		ArrayList<Integer> indexes = new ArrayList<>();
		for (int index = shapes.size(); --index >= 0;) {
			if (shapes.get(index).isSelected()) {
				indexes.add(index);
			}
		}
		return indexes;
	}

	public void removeSelectedShapes() {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			shapes.remove(selectedShapesIndexes().get(index));
		}
	}

	public DefaultListModel<Shape> getAllShapesDLM() {
		return shapes;
	}

	public ArrayList<Shape> getAllSelectedShapes() {
		ArrayList<Shape> selectedShapes = new ArrayList<>();
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			selectedShapes.add(shapes.get(selectedShapesIndexes().get(index)));
		}
		return selectedShapes;
	}

	public ArrayList<Integer> getAllSelectedShapeIndexes() {
		return this.selectedShapesIndexes();
	}

	public Optional<Shape> getShapeAt(Point point) {
		for (int index = shapes.size(); --index >= 0;) {
			if (shapes.get(index).contains(point)) {
				return Optional.ofNullable(shapes.get(index));
			}
		}
		return Optional.empty();
	}

	public void selectShapeAt(Integer index) {
		shapes.get(index).setSelected(true);
	}

	public void selectShape(Shape shape) {
		shape.setSelected(true);
	}

	public void deselectShape(Shape shape) {
		shape.setSelected(false);
	}

	public void deselectAllShapes() {
		ArrayList<Integer> selectedShapesIndexes = selectedShapesIndexes();
		for (int index = selectedShapesIndexes.size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes.get(index)).setSelected(false);
		}
	}

	public void moveSelectedShapesBy(double x, double y) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes().get(index)).moveBy((int) x, (int) y);
		}
	}

	public void updateColorOfSelectedShapes(Color color) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes().get(index)).setColor(color);
		}
	}

	public void updateBackgroundColorOfSelectedShapes(Color color) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			if (shapes.get(selectedShapesIndexes().get(index)) instanceof SurfaceShape) {
				((SurfaceShape) shapes.get(selectedShapesIndexes().get(index))).setBackgroundColor(color);
			}
		}
	}

	private void moveShapeToFront(Shape shape) {
		if (shapes.lastElement() != shape) {
			shapes.removeElement(shape);
			shapes.addElement(shape);
		}
		System.out.println(shapes.indexOf(shape));
	}

	private void moveShapeToBack(Shape shape) {
		if (shapes.firstElement() != shape) {
			shapes.removeElement(shape);
			shapes.insertElementAt(shape, 0);
		}
		System.out.println(shapes.indexOf(shape));
	}

	public void moveSelectedShapesBackward() {
		ArrayList<Shape> orderedSelectedShapes = this.getAllSelectedShapes();
		Collections.sort(orderedSelectedShapes);

		for (int selectedIndex = orderedSelectedShapes.size(); --selectedIndex >= 0;) {
			Shape selectedShape = orderedSelectedShapes.get(selectedIndex);
			int switchIndex = shapes.indexOf(selectedShape);
			if (switchIndex < orderedSelectedShapes.size()) {
				break;
			}
			Shape switchShape = shapes.getElementAt(switchIndex - 1);
			shapes.set(switchIndex, switchShape);
			shapes.set(switchIndex - 1, selectedShape);
		}
	}

	public void moveSelectedShapesForward() {
		ArrayList<Shape> orderedSelectedShapes = this.getAllSelectedShapes();
		Collections.sort(orderedSelectedShapes);

		for (int selectedIndex = 0; selectedIndex < orderedSelectedShapes.size(); selectedIndex++) {
			Shape selectedShape = orderedSelectedShapes.get(selectedIndex);
			int switchIndex = shapes.indexOf(selectedShape);
			if (switchIndex > shapes.size() - orderedSelectedShapes.size()) {
				continue;
			}
			Shape switchShape = shapes.getElementAt(switchIndex + 1);
			if (switchShape.isSelected()) {
				break;
			}
			shapes.set(switchIndex, switchShape);
			shapes.set(switchIndex + 1, selectedShape);
		}
	}

	public void moveSelectedShapesToBack() {
		ArrayList<Shape> orderedSelectedShapes = this.getAllSelectedShapes();
		Collections.sort(orderedSelectedShapes);
		for (int index = orderedSelectedShapes.size(); --index >= 0;) {
			this.moveShapeToBack(orderedSelectedShapes.get(index));
		}
	}

	public void moveSelectedShapesToFront() {
		ArrayList<Shape> orderedSelectedShapes = this.getAllSelectedShapes();
		Collections.sort(orderedSelectedShapes);
		for (int index = 0; index < orderedSelectedShapes.size(); index++) {
			this.moveShapeToFront(orderedSelectedShapes.get(index));
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
		int initSelectSize = selectedShapesIndexes().size();
		for (int index = 0; index < initSelectSize; index++) {
			Shape clone = this.getAllSelectedShapes().get(0).clone();
			this.deselectShape(this.getAllSelectedShapes().get(0));
			this.addShape(clone);
			this.selectShape(clone);
		}

	}

}
