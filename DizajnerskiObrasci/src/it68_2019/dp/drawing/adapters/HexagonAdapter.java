package it68_2019.dp.drawing.adapters;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.models.geometry.SurfaceShape;

public class HexagonAdapter extends SurfaceShape {
	Hexagon hexagon;

	public HexagonAdapter() {
		this(new Hexagon(0, 0, 0));
	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
		this.setBackgroundColor(super.getBackgroundColor());
		this.setColor(super.getColor());
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

	@Override
	public void setBackgroundColor(Color backgroundColor) {
		hexagon.setAreaColor(backgroundColor);
	}

	public Point getCenter() {
		return new Point(hexagon.getX(), hexagon.getY());
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
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);

	}

	public void setCenter(Point center) {
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
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	@Override
	public void setStartPoint(Point point) {
		this.setCenter(point);
	}

}
