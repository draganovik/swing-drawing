package oo.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x;
	private int y;

	public Point() {
	}

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	// OVERLOAD

	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.setSelected(selected);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		this.setColor(color);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point zero = new Point(0, 0);
			return (int) (this.distanceOf(zero) - ((Point) o).distanceOf(zero));
		}
		return 0;
	}

	@Override
	public boolean contains(Point point) {
		return this.distanceOf(point) <= 3;
	}

	public int distanceByXOf(Point point) {
		return Math.abs(point.x - this.x);
	}

	public int distanceByYOf(Point point) {
		return Math.abs(point.y - this.y);
	}

	public double distanceOf(Point point) {
		return Math.pow((Math.pow(distanceByXOf(point), 2) + Math.pow(distanceByYOf(point), 2)), 0.5);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(getX() - 2, getY(), getX() + 2, getY());
		g.drawLine(getX(), getY() - 2, getX(), getY() + 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getX() - 3, getY() - 3, 6, 6);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point) obj;
			if (this.x == point.getX() && this.y == point.getY()) {
				return true;
			}
			return false;
		}
		return false;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}

	public void setX(int x) {
		this.x = x;
	}

	// Primer Enkapsulacije u JAVI ^^^^^

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public String toString(String separator) {
		return this.x + separator + this.y;
	}

}
