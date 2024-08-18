package drawing.command;

import java.util.ArrayList;

import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class DuplicateSelected implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> selectedShapeIndexes;

	private CommandState state = CommandState.INITIALIZED;

	public DuplicateSelected(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
			throw new IllegalStateException("Command is already executed.");
		}
		state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

		this.selectedShapeIndexes = model.getAllSelectedShapeIndexes();

		model.duplicateSelected();

	}

	@Override
	public void undo() {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		model.removeSelectedShapes();
		for (int index = 0; index < this.selectedShapeIndexes.size(); index++) {
			model.selectShapeAt(selectedShapeIndexes.get(index));
		}
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command).append(" <").append("selectedShapeIndexes=")
				.append(selectedShapeIndexes.toString()).append(">");

		return output.toString();
	}

}
