package drawing.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Movable, Comparable<Object>, Cloneable, Serializable {

	private static final long serialVersionUID = 5098059299732788753L;
	private Color color = Color.DARK_GRAY;
	private transient boolean selected;

	public Shape() {
	}

	public Shape(Color color) {
		this.color = color;
	}

	public Shape(Color color, boolean selected) {
		this(color);
		this.selected = selected;
	}

	public abstract boolean contains(Point point);

	public abstract void draw(Graphics g);

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public abstract void moveBy(int byX, int byY);

	public abstract void setEndPoint(Point point);

	public abstract void setStartPoint(Point point);

	@Override
	public abstract Shape clone();

	public void updateFrom(Shape shape) throws Exception {
		this.setColor(shape.getColor());
		this.setSelected(shape.selected);
	}
}
