package drawing.command;

import java.util.List;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class MoveSelectedBackward implements ICommand {

	private final CanvasModel model;
	private List<Integer> initialSelectedShapesOrder;

	private CommandState state = CommandState.INITIALIZED;

	public MoveSelectedBackward(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
			throw new IllegalStateException("Command is already executed.");
		}
		state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

		this.initialSelectedShapesOrder = model.getAllSelectedShapeIndexes().reversed();

		model.moveSelectedShapesBackward();
	}

	@Override
	public void undo() {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		List<Shape> selectedShapes = model.getAllSelectedShapes();
		for (int i = selectedShapes.size(); --i >= 0;) {
			model.removeShape(selectedShapes.get(i));
			model.insertShape(selectedShapes.get(i), this.initialSelectedShapesOrder.get(i));
		}
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command);

		return output.toString();
	}

}
