package it68_2019.dp.drawing.adapters;

import java.awt.Graphics;

import hexagon.Hexagon;
import it68_2019.dp.drawing.models.geometry.Point;
import it68_2019.dp.drawing.models.geometry.SurfaceShape;

public class HexagonAdapter extends SurfaceShape {
	Hexagon hexagon;

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter() {
		this.hexagon = new Hexagon(0, 0, 0);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return hexagon.getR() - ((HexagonAdapter) o).getRadius();
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		hexagon.setAreaColor(this.getBackgroundColor());
	}

	@Override
	public void setStartPoint(Point point) {
		this.setCenter(point);
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

	public Point getCenter() {
		return new Point(hexagon.getX(), hexagon.getY());
	}

	public void setCenter(Point center) {
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) throws Exception {
		if (radius >= 0) {
			hexagon.setR(radius);
			return;
		}

		throw new NumberFormatException("Radius has to be a value grater than 0");

	}

	@Override
	public boolean contains(Point point) {
		return hexagon.doesContain(point.getX(), point.getY());
	}

	@Override
	public void draw(Graphics g) {
		hexagon.setBorderColor(this.getColor());
		hexagon.paint(g);
		this.fill(g);
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);

	}

}
