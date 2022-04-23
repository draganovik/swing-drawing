package drawing.components.toolbar;

import java.awt.Color;

import drawing.types.ToolAction;

public class ToolbarModel {
	private Color shapeBackground;
	private Color shapeColor;
	private ToolAction toolAction;

	public ToolbarModel() {
		toolAction = ToolAction.SELECT;
		shapeBackground = Color.LIGHT_GRAY;
		shapeColor = Color.DARK_GRAY;
	}

	public Color getShapeBackground() {
		return shapeBackground;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public ToolAction getToolAction() {
		return toolAction;
	}

	public void setShapeBackground(Color color) {
		shapeBackground = color;

	}

	public void setShapeColor(Color color) {
		shapeColor = color;

	}

	public void setToolAction(ToolAction toolAction) {
		this.toolAction = toolAction;
	}

}
