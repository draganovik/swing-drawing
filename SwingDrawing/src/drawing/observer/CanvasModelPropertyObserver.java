package drawing.observer;

import drawing.mvc.DrawingController;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CanvasModelPropertyObserver implements PropertyChangeListener {

    final DrawingController drawingController;

    public CanvasModelPropertyObserver(DrawingController drawingController) {
        this.drawingController = drawingController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("SelectionSizeChange")) {
            int newSelectionSize = (int) evt.getNewValue();
            drawingController.setEnabledToolbarDelete(newSelectionSize > 0 ? true : false);
            drawingController.setEnabledToolbarModify(newSelectionSize == 1 ? true : false);
            drawingController.setEnabledMenubarObjectOptions(newSelectionSize > 0 ? true : false);
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