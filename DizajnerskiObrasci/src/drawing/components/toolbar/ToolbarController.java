package drawing.components.toolbar;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

import drawing.adapters.HexagonAdapter;
import drawing.components.canvas.CanvasModel;
import drawing.components.canvas.CanvasView;
import drawing.geometry.Circle;
import drawing.geometry.Donut;
import drawing.geometry.Line;
import drawing.geometry.Point;
import drawing.geometry.Rectangle;
import drawing.geometry.Shape;
import drawing.modals.DlgManageCircle;
import drawing.modals.DlgManageDonut;
import drawing.modals.DlgManageHexagon;
import drawing.modals.DlgManageLine;
import drawing.modals.DlgManagePoint;
import drawing.modals.DlgManageRectangle;
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
		switch (model.getToolAction()) {
		case POINT:
		case LINE:
			canvasModel.deselectAllShapes();
			view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));
			view.btnToolbarBackground.setBackground(null);
			view.btnToolbarBackground.setEnabled(false);
			break;
		case RECTANGLE:
		case CIRCLE:
		case DONUT:
		case HEXAGON:
			canvasModel.deselectAllShapes();
			view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));
			view.btnToolbarBackground.setBackground(model.getShapeBackground());
			view.btnToolbarBackground.setEnabled(true);
		default:
			break;
		}

		canvasView.repaint();
	}

	public void setToolbarAction_BackgroundPicker() {
		Color selectedColor = getColorFromPicker("Choose Background Color", model.getShapeColor());
		if (selectedColor != null) {
			model.setShapeBackground(selectedColor);
			canvasModel.updateBackgroundColorOfSelectedShapes(selectedColor);
			view.btnToolbarBackground.setBackground(model.getShapeBackground());
			canvasView.repaint();
		}
	}

	public void setToolbarAction_ColorPicker() {
		Color selectedColor = getColorFromPicker("Choose Outline Color", model.getShapeBackground());
		if (selectedColor != null) {
			model.setShapeColor(selectedColor);
			canvasModel.updateColorOfSelectedShapes(selectedColor);
			view.btnToolbarColor.setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, model.getShapeColor()));
			canvasView.repaint();
		}
	}

	public void setToolbarAction_Delete() {
		canvasModel.removeSelectedShapes();
		canvasView.repaint();
	}

	public void setToolbarAction_Edit() {
		Shape selectedShape = canvasModel.getAllSelectedShapes().get(0);
		if (selectedShape instanceof Point) {
			DlgManagePoint modal = new DlgManagePoint((Point) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}
		if (selectedShape instanceof Line) {
			DlgManageLine modal = new DlgManageLine((Line) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}
		if (selectedShape instanceof Rectangle) {
			DlgManageRectangle modal = new DlgManageRectangle((Rectangle) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}

		if (selectedShape instanceof Donut) {
			DlgManageDonut modal = new DlgManageDonut((Donut) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}

		if (selectedShape instanceof Circle) {
			DlgManageCircle modal = new DlgManageCircle((Circle) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}

		if (selectedShape instanceof HexagonAdapter) {
			DlgManageHexagon modal = new DlgManageHexagon((HexagonAdapter) canvasModel.getAllSelectedShapes().get(0));
			showDialog(modal);
			canvasView.repaint();
			return;
		}
	}

	private void showDialog(JDialog modal) {
		modal.pack();
		modal.setLocationRelativeTo(canvasView);
		modal.setVisible(true);
	}

}
