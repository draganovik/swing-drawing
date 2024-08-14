package drawing.observer;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;

import drawing.mvc.views.ToolbarView;

public class ToolbarPropertyObserver implements PropertyChangeListener {

	final ToolbarView toolbarView;

	public ToolbarPropertyObserver(ToolbarView toolbarView) {
		this.toolbarView = toolbarView;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("SelectionSizeChange")) {
			int newSelectionSize = (int) evt.getNewValue();
			toolbarView.btnToolbarDelete.setEnabled(newSelectionSize > 0 ? true : false);
			toolbarView.btnToolbarModify.setEnabled(newSelectionSize == 1 ? true : false);
		}
		if (evt.getPropertyName().equals("SelectionBackgroundColorChange")) {
			Color newColor = (Color) evt.getNewValue();
			toolbarView.btnToolbarBackground.setBackground(newColor);
		}
		if (evt.getPropertyName().equals("SelectionColorChange")) {
			Color newColor = (Color) evt.getNewValue();
			toolbarView.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, newColor));
		}
	}
}