package drawing.command;

import java.awt.Color;
import java.util.ArrayList;

import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapesBackgroundColor implements ICommand {

	final CanvasModel model;
	final ArrayList<Color> initialSelectedShapesBackgroundColors;
	final Color updateColor;

	public UpdateModelSelectedShapesBackgroundColor(CanvasModel model, Color updateColor) {
		this.model = model;
		this.updateColor = updateColor;
		ArrayList<Shape> shapes = model.getAllSelectedShapes();

		initialSelectedShapesBackgroundColors = new ArrayList<>();

		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i) instanceof SurfaceShape) {
				initialSelectedShapesBackgroundColors.add(((SurfaceShape) shapes.get(i)).getBackgroundColor());
			} else {
				initialSelectedShapesBackgroundColors.add(null);
			}
		}
	}

	@Override
	public void execute() {
		model.updateBackgroundColorOfSelectedShapes(updateColor);
	}

	@Override
	public void undo() {
		ArrayList<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.updateShapeBackgroundColor(selectedShapes.get(i), initialSelectedShapesBackgroundColors.get(i));
		}
	}

}
