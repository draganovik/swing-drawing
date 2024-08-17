package drawing.mvc.models;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Stack;

import javax.swing.DefaultListModel;

import drawing.command.CommandGenerator;
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

	private Integer minDragDistanceForCreatingShape = 10;

	private Shape createdShape;

	private ToolAction toolAction;

	private DefaultListModel<String> commandLogListModel = new DefaultListModel<>();

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
		minDragDistanceForCreatingShape = 10;

		toolAction = ToolAction.SELECT;

		propertyChangeSupport.firePropertyChange("BackgroundColorChange", shapeBackground, Color.LIGHT_GRAY);
		propertyChangeSupport.firePropertyChange("ColorChange", shapeColor, Color.DARK_GRAY);

		shapeBackground = Color.LIGHT_GRAY;
		shapeColor = Color.DARK_GRAY;

		performedCommandStack.clear();
		revertedCommandStack.clear();
		commandLogListModel.clear();
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

	public Integer getMinDragDistanceForCreatingShape() {
		return this.minDragDistanceForCreatingShape;
	}

	public Boolean canDragCreateToPoint(Point point) {
		return this.startPoint.distanceOf(point) > minDragDistanceForCreatingShape;
	}

	public void setMinDragDistanceForCreatingShape(Integer size) {
		this.minDragDistanceForCreatingShape = size;
	}

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
		commandLogListModel.addElement(command.toString());
		this.clearCommand(command);
	}

	public void undoCommand() throws Exception {
		ICommand command = performedCommandStack.pop();
		command.undo();
		revertedCommandStack.push(command);
		commandLogListModel.addElement(command.toString());
	}

	public void redoCommand() throws Exception {
		ICommand command = revertedCommandStack.pop();
		command.redo();
		performedCommandStack.push(command);
		commandLogListModel.addElement(command.toString());
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

	/*
	 * Command Log operations
	 */

	public DefaultListModel<String> getCommandLogListModel() {
		return commandLogListModel;
	}

	public String getCommandLogString() {
		StringBuilder output = new StringBuilder();
		for (int index = 0; index < commandLogListModel.size(); index++) {
			output.append(commandLogListModel.get(index));
			output.append("\n");
		}

		return output.toString();
	}

	public void initFromCommandLogList(List<String> commandLogListModel, CanvasModel model) {
		clearWorkspace();
		
		for(int index = 0; index < commandLogListModel.size(); index++) {
			String line = commandLogListModel.get(index);
			ICommand command = CommandGenerator.generate(line, model);
			try {
				if(!line.contains("Unexecute")) {
					executeCommand(command);
				}
				else if(line.contains("Unexecute"))  {
					undoCommand();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
