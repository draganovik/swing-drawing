package drawing.mvc.models;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.swing.DefaultListModel;

import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;

public class CanvasModel {
	private boolean shiftDown = false;
	private DefaultListModel<Shape> shapes = new DefaultListModel<>();
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public void addShape(Shape shape) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shapes.addElement(shape);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void removeShape(Shape shape) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shapes.removeElement(shape);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void insertShape(Shape shape, int index) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shapes.insertElementAt(shape, index);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public int getShapeIndex(Shape shape) {
		return shapes.indexOf(shape);
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
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			shapes.remove(selectedShapesIndexes().get(index));
		}
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public DefaultListModel<Shape> getDefaultListModel() {
		return shapes;
	}

	public void setDefaultListModel(DefaultListModel<Shape> listModel) {
		this.shapes = listModel;
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
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shapes.get(index).setSelected(true);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void selectShape(Shape shape) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shape.setSelected(true);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void deselectShape(Shape shape) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shape.setSelected(false);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void deselectAllShapes() {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		ArrayList<Integer> selectedShapesIndexes = selectedShapesIndexes();
		for (int index = selectedShapesIndexes.size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes.get(index)).setSelected(false);
		}
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void moveSelectedShapesBy(double x, double y) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes().get(index)).moveBy((int) x, (int) y);
		}
	}

	public void updateColorOfSelectedShapes(Color color) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			updateShapeColor(shapes.get(selectedShapesIndexes().get(index)), color);
		}
	}

	public void updateShapeBackgroundColor(Shape shape, Color color) {
		if (shape instanceof SurfaceShape) {
			Color previousBackgroundColor = ((SurfaceShape) shape).getBackgroundColor();
			((SurfaceShape) shape).setBackgroundColor(color);
			propertyChangeSupport.firePropertyChange("BackgroundColorChange", previousBackgroundColor, color);
		}
	}

	public void updateShapeColor(Shape shape, Color color) {
		Color previousColor = shape.getColor();
		shape.setColor(color);
		propertyChangeSupport.firePropertyChange("ColorChange", previousColor, color);
	}

	public void updateBackgroundColorOfSelectedShapes(Color color) {
		for (int index = selectedShapesIndexes().size(); --index >= 0;) {
			if (shapes.get(selectedShapesIndexes().get(index)) instanceof SurfaceShape) {
				updateShapeBackgroundColor(shapes.get(selectedShapesIndexes().get(index)), color);
			}
		}
	}

	private void moveShapeToFront(Shape shape) {
		if (shapes.lastElement() != shape) {
			shapes.removeElement(shape);
			shapes.addElement(shape);
		}
	}

	private void moveShapeToBack(Shape shape) {
		if (shapes.firstElement() != shape) {
			shapes.removeElement(shape);
			shapes.insertElementAt(shape, 0);
		}
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

		for (Shape selectedShape : orderedSelectedShapes) {
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
		for (Shape orderedSelectedShape : orderedSelectedShapes) {
			this.moveShapeToFront(orderedSelectedShape);
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

	public void addPropertyObserver(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyObserver(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	public void removeAllShapes() {
		shapes.clear();
	}

}
