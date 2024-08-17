package drawing.observer;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import drawing.mvc.DrawingController;

public class ToolbarPropertyObserver implements PropertyChangeListener {

	final DrawingController drawingController;

	public ToolbarPropertyObserver(DrawingController drawingController) {
		this.drawingController = drawingController;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("SelectionSizeChange")) {
			int newSelectionSize = (int) evt.getNewValue();
			drawingController.setEnabledToolbarDelete(newSelectionSize > 0 ? true : false);
			drawingController.setEnabledToolbarModify(newSelectionSize > 0 ? true : false);
		}
		if (evt.getPropertyName().equals("BackgroundColorChange")) {
			Color newColor = (Color) evt.getNewValue();
			drawingController.setColorPickerShapeBackground(newColor);
		}
		if (evt.getPropertyName().equals("ColorChange")) {
			Color newColor = (Color) evt.getNewValue();
			drawingController.setColorPickerShapeColor(newColor);
		}
	}
}