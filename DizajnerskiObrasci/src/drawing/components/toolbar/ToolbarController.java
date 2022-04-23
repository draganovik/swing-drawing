package drawing.components.toolbar;

import java.awt.Color;

import javax.swing.JColorChooser;

import drawing.components.canvas.CanvasModel;
import drawing.components.canvas.CanvasView;
import drawing.types.ToolAction;

public class ToolbarController {
	private CanvasModel canvasModel;
	private CanvasView canvasView;
	private ToolbarModel model;
	private ToolbarView view;

	public ToolbarController(ToolbarModel model, CanvasModel canvasModel, ToolbarView view, CanvasView canvasView) {
		this.model = model;
		this.canvasModel = canvasModel;
		this.view = view;
		this.canvasView = canvasView;
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(view, title, initialColor);
		return color;
	}

	public void setToolbarAction(ToolAction action) {
		model.setToolAction(action);
		canvasModel.unselectAll();
		canvasView.repaint();
	}

	public void setToolbarAction_BackgroundPicker() {
		model.setShapeBackground(getColorFromPicker("Choose Background Color", model.getShapeColor()));
		view.btnToolbarBackground.setBackground(model.getShapeBackground());
	}

	public void setToolbarAction_ColorPicker() {
		model.setShapeColor(getColorFromPicker("Choose Outline Color", model.getShapeBackground()));
		view.btnToolbarColor.setBackground(model.getShapeColor());
	}

	public void setToolbarAction_Delete() {
		canvasModel.removeSelected();
		canvasView.repaint();
	}

	public void setToolbarAction_Modify() {
		model.toString();
	}

}
