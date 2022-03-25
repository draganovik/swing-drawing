package it68_2019.dp.drawing.components.toolbar;

import it68_2019.dp.drawing.types.ToolbarAction;

public class ToolbarModel {
	private ToolbarAction toolbarAction;
	
	public ToolbarModel() {
		toolbarAction = ToolbarAction.SELECT;
	}

	public ToolbarAction getToolbarAction() {
		return toolbarAction;
	}

	public void setToolbarAction(ToolbarAction toolbarAction) {
		this.toolbarAction = toolbarAction;
	}

}
