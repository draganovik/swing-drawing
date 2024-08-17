package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelShapeSelect implements ICommand {

	private final CanvasModel model;
	private final Integer selectedShapeIndex;

	private Boolean isExecuted = false;

	public UpdateModelShapeSelect(CanvasModel model, Integer shapeIndex) {
		this.model = model;
		this.selectedShapeIndex = shapeIndex;
	}

	@Override
	public void execute() {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		model.selectShapeAt(selectedShapeIndex);
	}

	@Override
	public void undo() {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.deselectShapeAt(selectedShapeIndex);
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("selectedShapeIndex=")
				.append(selectedShapeIndex.toString()).append(">");

		return output.toString();
	}

}
