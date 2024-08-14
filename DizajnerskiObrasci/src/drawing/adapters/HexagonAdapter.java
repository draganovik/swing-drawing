package drawing.adapters;

import java.awt.Color;
import java.awt.Graphics;

import drawing.geometry.Point;
import drawing.geometry.Shape;
import drawing.geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {
	private static final long serialVersionUID = -6568601569650306348L;
	private final Hexagon hexagon;
	private final Point adaptedCenter;

	public HexagonAdapter() {
		this(new Hexagon(0, 0, 0));
	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
		adaptedCenter = new Point(hexagon.getX(), hexagon.getY());
		this.setBackgroundColor(hexagon.getAreaColor());
		this.setColor(hexagon.getBorderColor());
	}

	public HexagonAdapter(Hexagon hexagon, Color color, Color backgroundColor) {
		this(hexagon);
		this.setColor(color);
		this.setBackgroundColor(backgroundColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return hexagon.getR() - ((HexagonAdapter) o).getRadius();
		}
		return 0;
	}

	@Override
	public boolean contains(Point point) {
		return hexagon.doesContain(point.getX(), point.getY());
	}

	@Override
	public void draw(Graphics g) {
		fill(g);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getColor());
		hexagon.paint(g);
	}

	@Override
	public Color getBackgroundColor() {
		return hexagon.getAreaColor();
	}

	public Point getCenter() {
		return adaptedCenter;
	}

	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	public int getRadius() {
		return hexagon.getR();
	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	@Override
	public void moveBy(int byX, int byY) {
		adaptedCenter.setX(hexagon.getX() + byX);
		adaptedCenter.setY(hexagon.getY() + byY);
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);

	}

	@Override
	public void setBackgroundColor(Color backgroundColor) {
		hexagon.setAreaColor(backgroundColor);
	}

	public void setCenter(Point center) {
		adaptedCenter.setX(center.getX());
		adaptedCenter.setY(center.getY());
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}

	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}

	@Override
	public void setEndPoint(Point point) {
		int radius = (int) new Point(hexagon.getX(), hexagon.getY()).distanceOf(point);
		try {
			this.setRadius(radius);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRadius(int radius) throws Exception {
		if (radius >= 0) {
			hexagon.setR(radius);
			return;
		}

		throw new NumberFormatException("Radius has to be a value grater than 0");

	}

	@Override
	public void setStartPoint(Point point) {
		this.setCenter(point);
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Hexagon").append("[").append("center=" + this.getCenter() + ", ")
				.append("radius=" + this.getRadius() + ", ").append("hashCode=" + this.hashCode()).append("]");
		return output.toString();
	}

	@Override
	public int hashCode() {
		return hexagon.hashCode();
	}

	@Override
	public HexagonAdapter clone() {
		Hexagon hexClone = new Hexagon(this.getCenter().getX(), this.getCenter().getY(), this.getRadius());
		hexClone.setAreaColor(this.getBackgroundColor());
		hexClone.setBorderColor(this.getColor());
		HexagonAdapter clone = new HexagonAdapter(hexClone);
		clone.setSelected(this.isSelected());
		return clone;
	}

	@Override
	public void updateFrom(Shape shape) throws Exception {
		if (!(shape instanceof HexagonAdapter)) {
			throw new NumberFormatException("Inner radius must be less than radius by at least 3.");
		}
		HexagonAdapter hexagonAdapter = (HexagonAdapter) shape;
		super.updateFrom(hexagonAdapter);
		this.setSelected(hexagonAdapter.isSelected());
		this.setCenter(hexagonAdapter.getCenter());
		this.setRadius(hexagonAdapter.getRadius());
		this.setColor(hexagonAdapter.getColor());
		this.setBackgroundColor(hexagonAdapter.getBackgroundColor());
	}

}
