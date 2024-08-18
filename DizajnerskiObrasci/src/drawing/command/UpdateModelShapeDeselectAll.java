package drawing.command;

import java.util.ArrayList;

import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class UpdateModelShapeDeselectAll implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> selectedIndexList;

	private CommandState state = CommandState.INITIALIZED;

	public UpdateModelShapeDeselectAll(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
			throw new IllegalStateException("Command is already executed.");
		}
		state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

		this.selectedIndexList = model.getAllSelectedShapeIndexes();

		model.deselectAllShapes();
	}

	@Override
	public void undo() {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		model.deselectAllShapes();
		for (Integer element : selectedIndexList) {
			model.selectShapeAt(element);
		}
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command).append(" <").append("selectedIndexList=")
				.append(selectedIndexList.toString()).append(">");

		return output.toString();
	}

}
