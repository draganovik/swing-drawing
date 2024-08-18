package drawing.mvc.models;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
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

	public ArrayList<Integer> getAllSelectedShapeIndexes() {
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
		for (int index = getAllSelectedShapeIndexes().size(); --index >= 0;) {
			shapes.remove(getAllSelectedShapeIndexes().get(index));
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
		for (int index = getAllSelectedShapeIndexes().size(); --index >= 0;) {
			selectedShapes.add(shapes.get(getAllSelectedShapeIndexes().get(index)));
		}
		return selectedShapes;
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

	public void deselectShapeAt(Integer index) {
		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		shapes.get(index).setSelected(false);
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void deselectAllShapes() {
		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		int previousSelectionSize = getAllSelectedShapeIndexes().size();
		ArrayList<Integer> selectedShapesIndexes = getAllSelectedShapeIndexes();
		for (int index = selectedShapesIndexes.size(); --index >= 0;) {
			shapes.get(selectedShapesIndexes.get(index)).setSelected(false);
		}
		int nextSelectionSize = getAllSelectedShapeIndexes().size();
		propertyChangeSupport.firePropertyChange("SelectionSizeChange", previousSelectionSize, nextSelectionSize);
	}

	public void moveSelectedShapesBy(double x, double y) {
		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		for (int index = getAllSelectedShapeIndexes().size(); --index >= 0;) {
			getAllSelectedShapes().get(index).moveBy((int) x, (int) y);
		}
	}

	public void updateColorOfSelectedShapes(Color color) {
		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		for (int index = getAllSelectedShapeIndexes().size(); --index >= 0;) {
			updateShapeColor(getAllSelectedShapes().get(index), color);
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
		for (int index = getAllSelectedShapeIndexes().size(); --index >= 0;) {
			Shape shape = getAllSelectedShapes().get(index);
			if (shape instanceof SurfaceShape) {
				updateShapeBackgroundColor(shape, color);
			}
		}
	}

	public void moveSelectedShapesBackward() {
		boolean isValid = false;

		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		for (int index = 1; index < shapes.size(); index++) {
			if (!shapes.get(index).isSelected() || shapes.get(index - 1).isSelected()) {
				continue;
			}

			isValid = true;
			Shape selectedShape = shapes.get(index);
			Shape switchShape = shapes.getElementAt(index - 1);
			shapes.set(index, switchShape);
			shapes.set(index - 1, selectedShape);
		}

		if (!isValid) {
			throw new IllegalArgumentException("Shapes are already at the back.");
		}
	}

	public void moveSelectedShapesForward() {
		boolean isValid = false;

		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		if (shapes.size() == getAllSelectedShapes().size()) {
			throw new IllegalArgumentException("Shapes are already at the front.");
		}

		for (int index = shapes.size() - 2; index >= 0; index--) {
			if (!shapes.get(index).isSelected() || shapes.get(index + 1).isSelected()) {
				continue;
			}

			isValid = true;
			Shape selectedShape = shapes.get(index);
			Shape switchShape = shapes.getElementAt(index + 1);
			shapes.set(index, switchShape);
			shapes.set(index + 1, selectedShape);
		}

		if (!isValid) {
			throw new IllegalArgumentException("Shapes are already at the front.");
		}
	}

	public void moveSelectedShapesToBack() {

		ArrayList<Integer> selectedShapeIndexes = getAllSelectedShapeIndexes();

		if (selectedShapeIndexes.isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		int firstIndex = getAllSelectedShapeIndexes().getFirst();

		if (firstIndex == selectedShapeIndexes.size() - 1) {
			throw new IllegalArgumentException("Shapes are already at the back.");
		}

		int movedShapes = 0;

		for (int index = shapes.size(); --index >= 0;) {
			if (!shapes.get(index + movedShapes).isSelected()) {
				continue;
			}

			Shape selectedShape = shapes.get(index + movedShapes);
			shapes.removeElement(selectedShape);
			shapes.insertElementAt(selectedShape, 0);
			movedShapes++;
		}
	}

	public void moveSelectedShapesToFront() {

		ArrayList<Integer> selectedShapeIndexes = getAllSelectedShapeIndexes();

		if (selectedShapeIndexes.isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}

		int lastIndex = getAllSelectedShapeIndexes().getLast();

		if (lastIndex == shapes.size() - selectedShapeIndexes.size()) {
			throw new IllegalArgumentException("Shapes are already at the front.");
		}

		int movedShapes = 0;

		for (int index = 0; index < shapes.size(); index++) {
			if (!shapes.get(index - movedShapes).isSelected()) {
				continue;
			}

			Shape selectedShape = shapes.get(index - movedShapes);
			shapes.removeElement(selectedShape);
			shapes.addElement(selectedShape);
			movedShapes++;
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
		if (getAllSelectedShapes().isEmpty()) {
			throw new IllegalArgumentException("There are no selected shapes.");
		}
		int initSelectSize = getAllSelectedShapeIndexes().size();
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
