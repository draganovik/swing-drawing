package drawing.command;

import drawing.geometry.Shape;

public class UpdateShapeProperties implements ICommand {

	final Shape shape;
	final Shape nextProperties;
	final Shape prevProperties;

	public UpdateShapeProperties(Shape shape, Shape update) {
		this.shape = shape;
		this.nextProperties = update.clone();
		this.prevProperties = shape.clone();
	}

	@Override
	public void execute() {
		shape.updateFrom(this.nextProperties);

	}

	@Override
	public void undo() {
		shape.updateFrom(this.prevProperties);
	}

}
