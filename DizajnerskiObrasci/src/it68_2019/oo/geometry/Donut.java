package it68_2019.oo.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); // mora biti prva linija koda
		this.setInnerRadius(innerRadius);

	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color backgroundColor) {
		this(center, radius, innerRadius, selected, color);
		this.setBackgroundColor(backgroundColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - innerRadius + 1, getCenter().getY() - innerRadius + 1,
				(this.innerRadius - 1) * 2, (this.innerRadius - 1) * 2);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - innerRadius - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + innerRadius - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - innerRadius - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + innerRadius - 3, 6, 6);
		}
	}

	@Override
	public void fill(Graphics g) {
		super.fill(g);
		g.setColor(new Color(255, 255, 255));
		g.fillOval(getCenter().getX() - innerRadius + 1, getCenter().getY() - innerRadius + 1, (innerRadius - 1) * 2,
				(innerRadius - 1) * 2);

	}

	@Override
	public double area() {
		return super.area() - (innerRadius * innerRadius * Math.PI);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut dn = (Donut) obj;
			if (super.getCenter().equals(dn.getCenter()) && super.getRadius() == dn.getRadius()
					&& dn.innerRadius == this.innerRadius) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean contains(Point point) {
		return super.contains(point) && super.getCenter().distanceOf(point) > this.innerRadius - 3;
	}

	@Override
	public void setRadius(int radius) throws Exception {
		if (radius - innerRadius < 3) {
			throw new NumberFormatException("Radius must be larger than inner radius by at least 3.");
		} else {
			super.setRadius(radius);
		}
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		if (super.getRadius() - innerRadius < 3) {
			throw new NumberFormatException("Inner radius must be less than radius by at least 3.");
		} else {
			this.innerRadius = innerRadius;
		}
	}

	@Override
	public String toString() {
		return "Circle [center=" + super.getCenter() + ", radius=" + super.getRadius() + ", Inner radius="
				+ this.getInnerRadius() + "]";
	}

}
