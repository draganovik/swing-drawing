package drawing.mvc.models;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

import drawing.command.ICommand;
import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import drawing.types.ToolAction;

public class WorkspaceModel {
	private Point startPoint;
	private Point dragPoint;
	private Point endPoint;

	private Color shapeBackground;
	private Color shapeColor;

	private Shape createdShape;

	private ToolAction toolAction;

	private Stack<ICommand> performedCommandStack = new Stack<>();
	private Stack<ICommand> revertedCommandStack = new Stack<>();

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public WorkspaceModel() {
		clearWorkspace();
	}

	public void clearWorkspace() {
		startPoint = null;
		endPoint = null;
		dragPoint = null;
		createdShape = null;

		toolAction = ToolAction.SELECT;

		propertyChangeSupport.firePropertyChange("BackgroundColorChange", shapeBackground, Color.LIGHT_GRAY);
		propertyChangeSupport.firePropertyChange("ColorChange", shapeColor, Color.DARK_GRAY);

		shapeBackground = Color.LIGHT_GRAY;
		shapeColor = Color.DARK_GRAY;

		performedCommandStack.clear();
		revertedCommandStack.clear();
	}

	/*
	 * Property Observers
	 */

	public void addPropertyObserver(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyObserver(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	/*
	 * Property States
	 */

	public void initCreatedShape(Shape shape) {
		shape.setColor(this.getShapeColor());
		if (shape instanceof SurfaceShape) {
			((SurfaceShape) shape).setBackgroundColor(this.getShapeBackground());
		}
		shape.setSelected(true);
		this.createdShape = shape;
	}

	public Shape getCreatedShape() {
		return this.createdShape;
	}

	public void clearCreatedShape() {
		this.createdShape = null;
	}

	public void setStartPoint(Point point) {
		this.startPoint = point;
	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public void setDragPoint(Point point) {
		this.dragPoint = point;
	}

	public Point getDragPoint() {
		return this.dragPoint;
	}

	public void setEndPoint(Point point) {
		this.endPoint = point;
	}

	public Point getEndPoint() {
		return this.endPoint;
	}

	public Color getShapeBackground() {
		return shapeBackground;
	}

	public void setShapeBackground(Color color) {
		shapeBackground = color;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public void setShapeColor(Color color) {
		shapeColor = color;
	}

	/*
	 * ToolAction
	 */

	public ToolAction getToolAction() {
		return toolAction;
	}

	public void setToolAction(ToolAction toolAction) {
		this.toolAction = toolAction;
	}

	/*
	 * Commands
	 */

	public void executeCommand(ICommand command) throws Exception {
		command.execute();
		performedCommandStack.push(command);
		revertedCommandStack.clear();
		System.out.println(performedCommandStack.size() + ". Command: " + command.getClass());
		this.clearCommand(command);
	}

	public void undoCommand() throws Exception {
		ICommand command = performedCommandStack.pop();
		command.undo();
		revertedCommandStack.push(command);
		System.out.println(performedCommandStack.size() + 1 + ". Undo command: " + command.getClass());
	}

	public void redoCommand() throws Exception {
		ICommand command = revertedCommandStack.pop();
		command.redo();
		performedCommandStack.push(command);
		System.out.println(performedCommandStack.size() + ". Redo command: " + command.getClass());
	}

	public Boolean canUndo() {
		return !performedCommandStack.isEmpty();
	}

	public Boolean canRedo() {
		return !revertedCommandStack.isEmpty();
	}

	public void clearCommand(ICommand command) {
		command = null;
	}
}
