package drawing.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private static final long serialVersionUID = -3226030232779664581L;
	private Point endPoint;
	private Point startPoint;

	public Line() {
	}

	public Line(Point startPoint) {
		this.startPoint = startPoint;
		this.endPoint = startPoint.clone();
	}

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
	public boolean contains(Point point) {
		return this.startPoint.distanceOf(point) + this.endPoint.distanceOf(point) - this.length() <= 0.05;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			if ((this.startPoint.equals(line.startPoint) && this.endPoint.equals(line.endPoint))
					|| (this.endPoint.equals(line.startPoint) && this.startPoint.equals(line.endPoint))) {
				return true;
			}
			return false;
		}
		return false;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public double length() {
		return startPoint.distanceOf(endPoint);
	}

	public Point middleOfLine() {
		return new Point((this.startPoint.getX() + this.endPoint.getX()) / 2,
				(this.startPoint.getY() + this.endPoint.getY()) / 2);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	@Override
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Line").append("[").append("startPoint=" + this.getStartPoint().toString(false) + ", ")
				.append("endPoint=" + this.getEndPoint().toString(false) + ", ")
				.append("color=" + this.getColor() + ", ").append("selected=" + this.isSelected()).append("]");
		return output.toString();
	}

	@Override
	public Shape clone() {
		Line clone = new Line(this.getStartPoint().clone(), this.getEndPoint().clone(), this.isSelected(),
				this.getColor());
		return clone;
	}

	@Override
	public void updateFrom(Shape shape) throws Exception {
		if (!(shape instanceof Line)) {
			throw new NumberFormatException("'updateFrom' shape must be of thhe same type.");
		}
		Line line = (Line) shape;
		super.updateFrom(line);
		this.setEndPoint(line.getEndPoint().clone());
		this.setStartPoint(line.getStartPoint().clone());
	}
}
