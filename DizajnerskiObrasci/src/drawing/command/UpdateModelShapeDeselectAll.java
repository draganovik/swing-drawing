package drawing.command;

import java.util.ArrayList;

import drawing.mvc.models.CanvasModel;

public class UpdateModelShapeDeselectAll implements ICommand {

	private final CanvasModel model;
	private ArrayList<Integer> selectedIndexList;

	private Boolean isExecuted = false;

	public UpdateModelShapeDeselectAll(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		this.selectedIndexList = model.getAllSelectedShapeIndexes();

		model.deselectAllShapes();
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.deselectAllShapes();
		for (Integer element : selectedIndexList) {
			model.selectShapeAt(element);
		}
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("selectedIndexList=")
				.append(selectedIndexList.toString()).append(">");

		return output.toString();
	}

}
