package it68_2019.dp.drawing.components.toolbar;

import it68_2019.dp.drawing.types.ToolbarAction;

public class ToolbarController {
	private ToolbarModel model;
	private ToolbarView view;
	
	public ToolbarController(ToolbarModel model, ToolbarView view) {
		this.model = model;
		this.view = view;
	}
	public void setToolbarAction(ToolbarAction action) {
		System.out.println(action);
		model.setToolbarAction(action);
	}
	private void setToolbarAction_ForegroundPicker() {
		model.toString();
	}
	private void setToolbarAction_BackgroundPicker() {
		model.toString();
	}
	private void setToolbarAction_Modify() {
		model.toString();
	}
	private void setToolbarAction_Delete() {
		model.toString();
	}

}
