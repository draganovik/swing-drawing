package drawing.command;

import drawing.geometry.GeometryGenerator;
import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

import java.awt.*;

public class CommandGenerator {
    public static ICommand generate(String line, CanvasModel model) {
        String commandName = line.split(" ")[1];
        String commandParams = "";

        if (line.contains("<")) {
            commandParams = line.substring(line.indexOf('<') + 1, line.indexOf('>'));
        }

        if (commandName.equals(AddShape.class.getSimpleName())) {
            String firstParam = commandParams.split("; ")[0];
            firstParam = firstParam.substring(firstParam.indexOf('=') + 1);
            Shape shape = GeometryGenerator.generate(firstParam);
            ICommand command = new AddShape(model, shape);
            return command;
        }

        if (commandName.equals(DuplicateSelected.class.getSimpleName())) {
            ICommand command = new DuplicateSelected(model);
            return command;
        }

        if (commandName.equals(RemoveSelected.class.getSimpleName())) {
            ICommand command = new RemoveSelected(model);
            return command;
        }

        if (commandName.equals(UpdateSelectedShapeBackground.class.getSimpleName())) {
            String firstParam = commandParams.split("; ")[0];
            firstParam = firstParam.substring(firstParam.indexOf('[') + 1, firstParam.indexOf(']'));
            Color color = new Color(Integer.parseInt(firstParam.split(",")[0].substring(2)),
                    Integer.parseInt(firstParam.split(",")[1].substring(2)),
                    Integer.parseInt(firstParam.split(",")[2].substring(2)));
            ICommand command = new UpdateSelectedShapeBackground(model, color);
            return command;
        }

        if (commandName.equals(MoveSelectedBackward.class.getSimpleName())) {
            ICommand command = new MoveSelectedBackward(model);
            return command;
        }

        if (commandName.equals(UpdateSelectedShapeColor.class.getSimpleName())) {
            String firstParam = commandParams.split("; ")[0];
            firstParam = firstParam.substring(firstParam.indexOf('[') + 1, firstParam.indexOf(']'));
            Color color = new Color(Integer.parseInt(firstParam.split(",")[0].substring(2)),
                    Integer.parseInt(firstParam.split(",")[1].substring(2)),
                    Integer.parseInt(firstParam.split(",")[2].substring(2)));
            ICommand command = new UpdateSelectedShapeColor(model, color);
            return command;
        }

        if (commandName.equals(MoveSelectedForward.class.getSimpleName())) {
            ICommand command = new MoveSelectedForward(model);
            return command;
        }

        if (commandName.equals(MoveSelected.class.getSimpleName())) {
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
            ICommand command = new MoveSelected(model, pointA, pointB);
            return command;
        }

        if (commandName.equals(MoveSelectedToBack.class.getSimpleName())) {
            ICommand command = new MoveSelectedToBack(model);
            return command;
        }

        if (commandName.equals(MoveSelectedToFront.class.getSimpleName())) {
            ICommand command = new MoveSelectedToFront(model);
            return command;
        }

        if (commandName.equals(DeselectShape.class.getSimpleName())) {
            Integer shapeIndex = Integer.parseInt(commandParams.substring(commandParams.indexOf('=') + 1));
            ICommand command = new DeselectShape(model, shapeIndex);
            return command;
        }

        if (commandName.equals(DeselectAllShapes.class.getSimpleName())) {
            ICommand command = new DeselectAllShapes(model);
            return command;
        }

        if (commandName.equals(SelectShape.class.getSimpleName())) {
            Integer shapeIndex = Integer.parseInt(commandParams.substring(commandParams.indexOf('=') + 1));
            ICommand command = new SelectShape(model, shapeIndex);
            return command;
        }

        if (commandName.equals(UpdateShape.class.getSimpleName())) {
            String firstParam = commandParams.split("; ")[0];
            firstParam = firstParam.substring(firstParam.indexOf('=') + 1);
            String secondParam = commandParams.split("; ")[1];
            secondParam = secondParam.substring(secondParam.indexOf('=') + 1);
            Shape next = GeometryGenerator.generate(secondParam);
            ICommand command = new UpdateShape(model, next);
            return command;
        }

        throw new IllegalStateException("Unexpected command found in log file.");

    }
}
