package it68_2019.dp.drawing.models.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;

	public double length() {
		return startPoint.distanceOf(endPoint);
	}
	public Line() {};

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		this.setColor(color);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getStartPoint().getX() - 3, getStartPoint().getY() - 3, 6, 6);
			g.drawRect(getEndPoint().getX() - 3, getEndPoint().getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}
	}

	public Point middleOfLine() {
		return new Point((this.startPoint.getX() + this.endPoint.getX()) / 2,
				(this.startPoint.getY() + this.endPoint.getY()) / 2);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			if (this.startPoint.equals(line.startPoint) && this.endPoint.equals(line.endPoint)) {
				return true;
			}
			if (this.endPoint.equals(line.startPoint) && this.startPoint.equals(line.endPoint)) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean contains(Point point) {
		return this.startPoint.distanceOf(point) + this.endPoint.distanceOf(point) - this.length() <= 0.05;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	@Override
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	@Override
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public String toString() {
		return this.startPoint.toString() + " --> " + this.endPoint.toString();
	}
}
