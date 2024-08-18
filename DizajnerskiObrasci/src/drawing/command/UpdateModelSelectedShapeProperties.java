package drawing.command;

import drawing.geometry.Shape;
import drawing.mvc.models.CanvasModel;
import drawing.types.CommandState;

public class UpdateModelSelectedShapeProperties implements ICommand {

	private final CanvasModel model;
	private Shape prevProperties;
	private final Shape nextProperties;

	private CommandState state = CommandState.INITIALIZED;

	public UpdateModelSelectedShapeProperties(CanvasModel model, Shape update) {
		this.model = model;
		this.nextProperties = update.clone();
	}

	@Override
	public void execute() throws Exception {
		if (state != CommandState.INITIALIZED && state != CommandState.UNDO) {
			throw new IllegalStateException("Command is already executed.");
		}
		state = state == CommandState.INITIALIZED ? CommandState.EXECUTE : CommandState.REDO;

		this.prevProperties = model.getAllSelectedShapes().get(0).clone();

		model.getAllSelectedShapes().get(0).updateFrom(this.nextProperties);
	}

	@Override
	public void undo() throws Exception {
		if (state != CommandState.EXECUTE && state != CommandState.REDO) {
			throw new IllegalStateException("Command is not executed.");
		}
		state = CommandState.UNDO;

		model.getAllSelectedShapes().get(0).updateFrom(this.prevProperties);
	}

	@Override
	public String toString() {
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state.toString()).append(" ").append(command).append(" <").append("prevProperties=")
				.append(prevProperties.toString()).append("; ").append("nextProperties=")
				.append(nextProperties.toString()).append(">");

		return output.toString();
	}

}
