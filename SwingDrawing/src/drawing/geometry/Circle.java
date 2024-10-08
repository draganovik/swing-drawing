package drawing.geometry;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Circle extends SurfaceShape {
    private static final long serialVersionUID = -1719656449150869603L;
    private Point center;
    protected int radius;

    public Circle() {
    }

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

    public double area() {
        return radius * radius * Math.PI;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Circle) {
            return this.getRadius() - ((Circle) o).getRadius();
        }
        return 0;
    }

    @Override
    public boolean contains(Point point) {
        return this.center.distanceOf(point) < this.radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        this.fill(g);
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
    public void fill(Graphics g) {
        g.setColor(getBackgroundColor());
        ((Graphics2D) g).fill(getGraphicsArea());

    }

    public void fill(Graphics g, Area Shape) {
        g.setColor(getBackgroundColor());
        Area areaCircle = getGraphicsArea();
        areaCircle.subtract(Shape);
        ((Graphics2D) g).fill(areaCircle);

    }

    public Point getCenter() {
        return center;
    }

    protected Area getGraphicsArea() {
        return new Area(new Ellipse2D.Double(center.getX() - radius + 1, center.getY() - radius + 1, (radius - 1) * 2,
                (radius - 1) * 2));
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void moveBy(int byX, int byY) {
        this.center.moveBy(byX, byY);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void setEndPoint(Point point) {
        radius = (int) Math.abs(center.distanceOf(point));
    }

    public void setRadius(int radius) throws Exception {
        if (radius > 3) {
            this.radius = radius;
        } else {

            throw new NumberFormatException("Radius has to be a value grater than 3");
        }
    }

    @Override
    public void setStartPoint(Point point) {
        center = point;

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Circle").append("[").append("center=" + this.getCenter().toString(true) + ", ")
                .append("radius=" + this.getRadius() + ", ").append("color=" + this.getColor() + ", ")
                .append("background=" + this.getBackgroundColor() + ", ").append("selected=" + this.isSelected())
                .append("]");
        return output.toString();
    }

    @Override
    public Circle clone() {
        Circle clone = new Circle(this.getCenter().clone(), this.getRadius(), this.isSelected(), this.getColor(),
                this.getBackgroundColor());
        return clone;
    }

    @Override
    public void updateFrom(Shape shape) throws Exception {
        if (!(shape instanceof Circle)) {
            throw new NumberFormatException("'updateFrom' shape must be of thhe same type.");
        }
        Circle circle = (Circle) shape;
        super.updateFrom(circle);
        this.setCenter(circle.getCenter().clone());
        this.setRadius(circle.getRadius());
    }

}
