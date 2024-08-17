package drawing.command;

import java.util.ArrayList;

import drawing.mvc.models.CanvasModel;

public class UpdateModelDuplicateSelectedShape implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> selectedShapeIndexes;

	private Boolean isExecuted = false;

	public UpdateModelDuplicateSelectedShape(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;
		
		this.selectedShapeIndexes = model.getAllSelectedShapeIndexes();

		model.duplicateSelected();

	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.removeSelectedShapes();
		for (int index = 0; index < this.selectedShapeIndexes.size(); index++) {
			model.selectShapeAt(selectedShapeIndexes.get(index));
		}
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Unexecute " : "Execute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("selectedShapeIndexes=")
				.append(selectedShapeIndexes.toString()).append(">");

		return output.toString();
	}

}
