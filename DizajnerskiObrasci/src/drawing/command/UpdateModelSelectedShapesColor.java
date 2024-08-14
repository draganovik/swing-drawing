package drawing.command;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesColor implements ICommand {

	final CanvasModel model;
	final ArrayList<Color> initialSelectedShapesColors;
	final Color updateColor;

	public UpdateModelSelectedShapesColor(CanvasModel model, Color updateColor) {
		this.model = model;
		this.updateColor = updateColor;
		ArrayList<Shape> shapes = model.getAllSelectedShapes();

		initialSelectedShapesColors = new ArrayList<>();

		for (int i = 0; i < shapes.size(); i++) {
			initialSelectedShapesColors.add(shapes.get(i).getColor());
		}
	}

	@Override
	public void execute() {
		model.updateColorOfSelectedShapes(updateColor);
	}

	@Override
	public void undo() {
		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.updateShapeColor(selectedShapes.get(i), initialSelectedShapesColors.get(i));
		}
	}

}
