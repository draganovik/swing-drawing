package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;

public class UpdateModelSelectedShapeProperties implements ICommand {

	private final CanvasModel model;
	private Shape prevProperties;
	private final Shape nextProperties;

	private Boolean isExecuted = false;

	public UpdateModelSelectedShapeProperties(CanvasModel model, Shape update) {
		this.model = model;
		this.nextProperties = update.clone();
	}

	@Override
	public void execute() throws Exception {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		this.prevProperties = model.getAllSelectedShapes().get(0).clone();

		model.getAllSelectedShapes().get(0).updateFrom(this.nextProperties);
	}

	@Override
	public void undo() throws Exception {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		model.getAllSelectedShapes().get(0).updateFrom(this.prevProperties);
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("prevProperties=").append(prevProperties.toString())
				.append("; ").append("nextProperties=").append(nextProperties.toString()).append(">");

		return output.toString();
	}

}
