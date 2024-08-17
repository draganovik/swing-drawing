package drawing.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {
	private static final long serialVersionUID = 296561039882161461L;
	private Point drawStartPoint;
	private int height;
	private Point upperLeftPoint;
	private int width;

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

	public int area() {
		return height * width;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return ((this.area() - ((Rectangle) o).area()));
		}
		return 0;
	}

	@Override
	public boolean contains(Point point) {
		return this.upperLeftPoint.getX() <= point.getX() && this.upperLeftPoint.getY() <= point.getY()
				&& point.getX() <= this.upperLeftPoint.getX() + this.width
				&& point.getY() <= this.upperLeftPoint.getY() + this.height;
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
	public void fill(Graphics g) {
		g.setColor(getBackgroundColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.getWidth() - 2,
				this.getHeight() - 2);

	}

	public int getHeight() {
		return height;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public void setEndPoint(Point point) {
		upperLeftPoint = new Point(Math.min(drawStartPoint.getX(), point.getX()),
				Math.min(drawStartPoint.getY(), point.getY()));

		width = drawStartPoint.distanceByXOf(point);
		height = drawStartPoint.distanceByYOf(point);

	}

	public void setHeight(int height) {
		if (height < 6) {
			throw new NumberFormatException("Height must me at least 6.");
		} else {
			this.height = height;
		}
	}

	@Override
	public void setStartPoint(Point point) {
		this.upperLeftPoint = point;
		this.drawStartPoint = point;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public void setWidth(int width) {
		if (width < 6) {
			throw new NumberFormatException("Width must me at least 6.");
		} else {
			this.width = width;
		}
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Rectangle").append("[")
				.append("upperLeftPoint=" + this.getUpperLeftPoint().toString(true) + ", ")
				.append("width=" + this.getWidth() + ", ").append("height=" + this.getHeight() + ", ")
				.append("color=" + this.getColor() + ", ").append("background=" + this.getBackgroundColor() + ", ")
				.append("selected=" + this.isSelected()).append("]");
		return output.toString();
	}

	@Override
	public Rectangle clone() {
		Rectangle clone = new Rectangle(this.getUpperLeftPoint().clone(), this.getWidth(), this.getHeight(),
				this.isSelected(), this.getColor(), this.getBackgroundColor());
		return clone;
	}

	@Override
	public void updateFrom(Shape shape) throws Exception {
		if (!(shape instanceof Rectangle)) {
			throw new NumberFormatException("Inner radius must be less than radius by at least 3.");
		}
		Rectangle rectangle = (Rectangle) shape;
		super.updateFrom(rectangle);
		this.setUpperLeftPoint(rectangle.getUpperLeftPoint().clone());
		this.setWidth(rectangle.getWidth());
		this.setHeight(rectangle.getHeight());
	}

}
