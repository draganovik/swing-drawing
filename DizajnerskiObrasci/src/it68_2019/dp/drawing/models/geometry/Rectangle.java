package it68_2019.dp.drawing.models.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {
	private Point upperLeftPoint;
	private Point drawStartPoint;
	private int width;
	private int height;

	public Rectangle() {
	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.height = height;
		this.width = width;
		this.upperLeftPoint = upperLeftPoint;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this.height = height;
		this.width = width;
		this.upperLeftPoint = upperLeftPoint;
		this.setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color) {
		this(upperLeftPoint, width, height, selected);
		this.setColor(color);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color,
			Color backgroundColor) {
		this(upperLeftPoint, width, height, selected, color);
		this.setBackgroundColor(backgroundColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return ((this.area() - ((Rectangle) o).area()));
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getBackgroundColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.getWidth() - 2,
				this.getHeight() - 2);

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.getWidth(), this.getHeight());
		fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() + width - 3, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() + height - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() + width - 3, upperLeftPoint.getY() + height - 3, 6, 6);
		}

	}

	public int area() {
		return height * width;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rec = (Rectangle) obj;
			if (this.upperLeftPoint.equals(rec.upperLeftPoint) && this.width == rec.width
					&& this.height == rec.height) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean contains(Point point) {
		return this.upperLeftPoint.getX() <= point.getX() && this.upperLeftPoint.getY() <= point.getY()
				&& point.getX() <= this.upperLeftPoint.getX() + this.width
				&& point.getY() <= this.upperLeftPoint.getY() + this.height;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Upper left point=" + this.upperLeftPoint + ", width=" + width + ", height=" + height;
	}

	@Override
	public void setStartPoint(Point point) {
		this.upperLeftPoint = point;
		this.drawStartPoint = point;
	}

	@Override
	public void setEndPoint(Point point) {
		upperLeftPoint = new Point(Math.min(drawStartPoint.getX(), point.getX()),
				Math.min(drawStartPoint.getY(), point.getY()));

		width = drawStartPoint.distanceByXOf(point);
		height = drawStartPoint.distanceByYOf(point);

	}

}
