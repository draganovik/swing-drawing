package drawing.command;

import java.awt.Color;

import drawing.geometry.GeometryGenerator;
import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class CommandGenerator {
	public static ICommand generate(String line, CanvasModel model) {
		String commandName = line.split(" ")[1];
		String commandParams = "";

		if (line.contains("<")) {
			commandParams = line.substring(line.indexOf('<') + 1, line.indexOf('>'));
		}

		if (commandName.equals(UpdateModelAddShape.class.getSimpleName())) {
			String firstParam = commandParams.split("; ")[0];
			firstParam = firstParam.substring(firstParam.indexOf('=') + 1);
			Shape shape = GeometryGenerator.generate(firstParam);
			ICommand command = new UpdateModelAddShape(model, shape);
			return command;
		}

		if (commandName.equals(UpdateModelDuplicateSelectedShape.class.getSimpleName())) {
			ICommand command = new UpdateModelDuplicateSelectedShape(model);
			return command;
		}

		if (commandName.equals(UpdateModelRemoveSelectedShapes.class.getSimpleName())) {
			ICommand command = new UpdateModelRemoveSelectedShapes(model);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesBackgroundColor.class.getSimpleName())) {
			String firstParam = commandParams.split("; ")[0];
			firstParam = firstParam.substring(firstParam.indexOf('[') + 1, firstParam.indexOf(']'));
			Color color = new Color(Integer.parseInt(firstParam.split(",")[0].substring(2)),
					Integer.parseInt(firstParam.split(",")[1].substring(2)),
					Integer.parseInt(firstParam.split(",")[2].substring(2)));
			ICommand command = new UpdateModelSelectedShapesBackgroundColor(model, color);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesBackward.class.getSimpleName())) {
			ICommand command = new UpdateModelSelectedShapesBackward(model);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesColor.class.getSimpleName())) {
			String firstParam = commandParams.split("; ")[0];
			firstParam = firstParam.substring(firstParam.indexOf('[') + 1, firstParam.indexOf(']'));
			Color color = new Color(Integer.parseInt(firstParam.split(",")[0].substring(2)),
					Integer.parseInt(firstParam.split(",")[1].substring(2)),
					Integer.parseInt(firstParam.split(",")[2].substring(2)));
			ICommand command = new UpdateModelSelectedShapesColor(model, color);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesForward.class.getSimpleName())) {
			ICommand command = new UpdateModelSelectedShapesForward(model);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesPosition.class.getSimpleName())) {
			String firstParam = commandParams.split("; ")[0];
			firstParam = firstParam.substring(firstParam.indexOf('[') + 1, firstParam.indexOf(']'));
			String aX = firstParam.split(",")[0].substring(firstParam.split(",")[0].indexOf("=") + 1);
			String aY = firstParam.split(",")[1].substring(firstParam.split(",")[1].indexOf("=") + 1);
			Point pointA = new Point(Integer.parseInt(aX), Integer.parseInt(aY));

			String secondParam = commandParams.split("; ")[1];
			secondParam = secondParam.substring(secondParam.indexOf('[') + 1, secondParam.indexOf(']'));
			String bX = secondParam.split(",")[0].substring(secondParam.split(",")[0].indexOf("=") + 1);
			String bY = secondParam.split(",")[1].substring(secondParam.split(",")[1].indexOf("=") + 1);
			Point pointB = new Point(Integer.parseInt(bX), Integer.parseInt(bY));
			ICommand command = new UpdateModelSelectedShapesPosition(model, pointA, pointB);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesToBack.class.getSimpleName())) {
			ICommand command = new UpdateModelSelectedShapesToBack(model);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapesToFront.class.getSimpleName())) {
			ICommand command = new UpdateModelSelectedShapesToFront(model);
			return command;
		}

		if (commandName.equals(UpdateModelShapeDeselect.class.getSimpleName())) {
			Integer shapeIndex = Integer.parseInt(commandParams.substring(commandParams.indexOf('=') + 1));
			ICommand command = new UpdateModelShapeDeselect(model, shapeIndex);
			return command;
		}

		if (commandName.equals(UpdateModelShapeDeselectAll.class.getSimpleName())) {
			ICommand command = new UpdateModelShapeDeselectAll(model);
			return command;
		}

		if (commandName.equals(UpdateModelShapeSelect.class.getSimpleName())) {
			Integer shapeIndex = Integer.parseInt(commandParams.substring(commandParams.indexOf('=') + 1));
			ICommand command = new UpdateModelShapeSelect(model, shapeIndex);
			return command;
		}

		if (commandName.equals(UpdateModelSelectedShapeProperties.class.getSimpleName())) {
			String firstParam = commandParams.split("; ")[0];
			firstParam = firstParam.substring(firstParam.indexOf('=') + 1);
			String secondParam = commandParams.split("; ")[1];
			secondParam = secondParam.substring(secondParam.indexOf('=') + 1);
			Shape next = GeometryGenerator.generate(secondParam);
			ICommand command = new UpdateModelSelectedShapeProperties(model, next);
			return command;
		}

		throw new IllegalStateException("Unexpected command found in log file.");

	}
}
