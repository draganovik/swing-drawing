package it68_2019.dp.drawing.models.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	private Point center;
	private int radius;

	public Circle() {};
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this.center = center;
		this.setSelected(selected);
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}

	public Circle(Point center, int radius, boolean selected, Color color, Color backgroundColor) {
		this(center, radius, selected, color);
		this.setBackgroundColor(backgroundColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return this.getRadius() - ((Circle) o).getRadius();
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - radius - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() + radius - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - radius - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() + radius - 3, 6, 6);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getBackgroundColor());
		g.fillOval(center.getX() - radius + 1, center.getY() - radius + 1, (radius - 1) * 2, (radius - 1) * 2);

	}

	public double area() {
		return radius * radius * Math.PI;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle cl = (Circle) obj;
			if (this.center.equals(cl.center) && this.radius == cl.radius) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean contains(Point point) {
		return this.center.distanceOf(point) < this.radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {

			throw new NumberFormatException("Radius has to be a value grater than 0");
		}
	}

	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + "]";
	}

	@Override
	public void setStartPoint(Point point) {
		center = point;
		
	}

	@Override
	public void setEndPoint(Point point) {
		radius = (int) Math.abs(center.distanceOf(point));
	}

}
