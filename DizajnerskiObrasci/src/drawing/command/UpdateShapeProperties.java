package drawing.command;

import drawing.geometry.Shape;

public class UpdateShapeProperties implements ICommand {

	final Shape shape;
	final Shape prevProperties;
	final Shape nextProperties;

	public UpdateShapeProperties(Shape shape, Shape update) {
		this.shape = shape;
		this.nextProperties = update.clone();
		this.prevProperties = shape.clone();
	}

	@Override
	public void execute() throws Exception {
		System.out.println("before");
		System.out.println(shape.toString());
		System.out.println(nextProperties.toString());
		this.shape.updateFrom(this.nextProperties);
		System.out.println("after");
		System.out.println(shape.toString());
		System.out.println(nextProperties.toString());
	}

	@Override
	public void undo() throws Exception {
		this.shape.updateFrom(this.prevProperties);
	}

}
