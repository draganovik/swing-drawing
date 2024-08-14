package drawing.command;

public interface ICommand {
	public void execute();

	public void undo();

	public default void redo() {
		this.execute();
	}
}
