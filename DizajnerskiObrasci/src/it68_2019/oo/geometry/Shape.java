package it68_2019.oo.geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Movable, Comparable<Object> {

	private boolean selected;
	private Color color = Color.DARK_GRAY;

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

	@Override
	public abstract void moveBy(int byX, int byY);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
