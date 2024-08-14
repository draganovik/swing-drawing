package drawing.command;

public interface ICommand {
	public void execute() throws Exception;

	public void undo() throws Exception;

	public default void redo() throws Exception {
		this.execute();
	}
}
