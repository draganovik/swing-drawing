package drawing.components.toolbar;

import java.awt.Color;

import javax.swing.BorderFactory;
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

		view.btnToolbarColor.setOpaque(true);
		view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));

		view.btnToolbarBackground.setOpaque(true);
		view.btnToolbarBackground.setBackground(model.getShapeBackground());
		view.btnToolbarBackground.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
	}

	public void setShapeColor(Color color) {
		model.setShapeColor(color);
		view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));
	}

	public void setShapeBackground(Color color) {
		model.setShapeBackground(color);
		view.btnToolbarBackground.setBackground(model.getShapeBackground());
	}

	private Color getColorFromPicker(String title, Color initialColor) {
		Color color = JColorChooser.showDialog(view, title, initialColor);
		return color;
	}

	public void setToolbarAction(ToolAction action) {
		model.setToolAction(action);
		canvasModel.deselectAllShapes();
		canvasView.repaint();
	}

	public void setToolbarAction_BackgroundPicker() {
		Color selectedColor = getColorFromPicker("Choose Background Color", model.getShapeColor());
		if (selectedColor != null) {
			model.setShapeBackground(selectedColor);
			canvasModel.UpdateBackgroundColorOfSelectedShapes(selectedColor);
			view.btnToolbarBackground.setBackground(model.getShapeBackground());
			canvasView.repaint();
		}
	}

	public void setToolbarAction_ColorPicker() {
		Color selectedColor = getColorFromPicker("Choose Outline Color", model.getShapeBackground());
		if (selectedColor != null) {
			model.setShapeColor(selectedColor);
			canvasModel.UpdateColorOfSelectedShapes(selectedColor);
			view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));
			canvasView.repaint();
		}
	}

	public void setToolbarAction_Delete() {
		canvasModel.removeSelectedShapes();
		canvasView.repaint();
	}

	public void setToolbarAction_Modify() {
		model.toString();
	}

}
