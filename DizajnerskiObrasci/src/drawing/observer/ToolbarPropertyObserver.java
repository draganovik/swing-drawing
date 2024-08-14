package drawing.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	}
}