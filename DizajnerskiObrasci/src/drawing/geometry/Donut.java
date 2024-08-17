package drawing.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private static final long serialVersionUID = 3365059876952620250L;
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
	public double area() {
		return super.area() - (innerRadius * innerRadius * Math.PI);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	@Override
	public boolean contains(Point point) {
		return super.contains(point) && super.getCenter().distanceOf(point) > this.innerRadius - 3;
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
	public void fill(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		Area aInnerCircle = new Area(new Ellipse2D.Double(getCenter().getX() - innerRadius + 1,
				getCenter().getY() - innerRadius + 1, (innerRadius - 1) * 2, (innerRadius - 1) * 2));
		super.fill(g, aInnerCircle);

	}

	public int getInnerRadius() {
		return innerRadius;
	}

	@Override
	public void setEndPoint(Point point) {
		super.setEndPoint(point);
		innerRadius = (int) (radius * 0.45);
	}

	public void setInnerRadius(int innerRadius) {
		if ((super.getRadius() - innerRadius) < 2 || innerRadius < 2) {
			throw new NumberFormatException("Inner radius must be more than 2, and maximum radius-2");
		} else {
			this.innerRadius = innerRadius;
		}
	}

	@Override
	public void setRadius(int radius) throws Exception {
		if ((radius - innerRadius) < 2) {
			throw new NumberFormatException("Radius must be larger than inner radius by at least 2.");
		} else {
			super.setRadius(radius);
		}
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Donut").append("[").append("center=" + this.getCenter().toString(true) + ", ")
				.append("radius=" + this.getRadius() + ", ").append("innerRadius=" + this.getInnerRadius() + ", ")
				.append("color=" + this.getColor() + ", ").append("background=" + this.getBackgroundColor() + ", ")
				.append("selected=" + this.isSelected()).append("]");
		return output.toString();
	}

	@Override
	public Donut clone() {
		Donut clone = new Donut(this.getCenter().clone(), this.getRadius(), this.getInnerRadius(), this.isSelected(),
				this.getColor(), this.getBackgroundColor());
		return clone;
	}

	@Override
	public void updateFrom(Shape shape) throws Exception {
		if (!(shape instanceof Donut)) {
			throw new NumberFormatException("'updateFrom' shape must be of thhe same type.");
		}
		Donut donut = (Donut) shape;
		super.updateFrom(donut);
		this.setInnerRadius(donut.getInnerRadius());
	}

}
