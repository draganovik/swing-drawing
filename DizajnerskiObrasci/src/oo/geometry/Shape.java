package oo.geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Movable, Comparable<Object> {

	private Color color = Color.DARK_GRAY;
	private boolean selected;

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

	public Color getColor() {
		return color;
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public abstract void moveBy(int byX, int byY);

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
