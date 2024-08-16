package drawing.command;

import drawing.geometry.Shape;

public class UpdateShapeProperties implements ICommand {

	final Shape shape;
	final Shape prevProperties;
	final Shape nextProperties;

	private Boolean isExecuted = false;

	public UpdateShapeProperties(Shape shape, Shape update) {
		this.shape = shape;
		this.nextProperties = update.clone();
		this.prevProperties = shape.clone();
	}

	@Override
	public void execute() throws Exception {
		if (isExecuted) {
			throw new IllegalStateException("Command is already executed.");
		}
		isExecuted = true;

		this.shape.updateFrom(this.nextProperties);
	}

	@Override
	public void undo() throws Exception {
		if (!isExecuted) {
			throw new IllegalStateException("Command is not executed.");
		}
		isExecuted = false;

		this.shape.updateFrom(this.prevProperties);
	}

	@Override
	public String toString() {
		String state = isExecuted ? "Execute " : "Unexecute ";
		String command = this.getClass().getSimpleName();

		StringBuilder output = new StringBuilder();
		output.append(state).append(command).append(" <").append("prevProperties=").append(prevProperties.toString())
				.append("nextProperties=").append(nextProperties.toString()).append(">");

		return output.toString();
	}

}
