package drawing.geometry;

import drawing.adapters.HexagonAdapter;

public class GeometryGenerator {
	public static Shape generate(String line) {

		String shapeName = line.substring(0, line.indexOf('['));

		String shapeParams = line.substring(line.indexOf('[') + 1, line.lastIndexOf(']'));

		if (shapeName.equals(Circle.class.getSimpleName())) {
			Shape shape = new Circle();
			return shape;
		}

		if (shapeName.equals(Donut.class.getSimpleName())) {
			Shape shape = new Donut();
			return shape;
		}

		if (shapeName.equals(Line.class.getSimpleName())) {
			Shape shape = new Line();
			return shape;
		}

		if (shapeName.equals(Point.class.getSimpleName())) {
			Shape shape = new Point();
			return shape;
		}

		if (shapeName.equals(Rectangle.class.getSimpleName())) {
			String ulPoint = shapeParams.substring(shapeParams.indexOf("[") + 1, shapeParams.indexOf("]"));
			int ulpX = Integer.parseInt(ulPoint.split(", ")[0].substring(ulPoint.split(", ")[0].indexOf("=") + 1));
			int ulpY = Integer.parseInt(ulPoint.split(", ")[1].substring(ulPoint.split(", ")[1].indexOf("=") + 1));
			Point ulp = new Point(ulpX, ulpY);
			shapeParams = shapeParams.split("], ")[1];
			String width = shapeParams.split(", ")[0].substring(shapeParams.split(", ")[0].indexOf("=") + 1);
			String height = shapeParams.split(", ")[1].substring(shapeParams.split(", ")[1].indexOf("=") + 1);
			Shape shape = new Rectangle(ulp, Integer.parseInt(width), Integer.parseInt(height));
			return shape;
		}

		if (shapeName.equals("Hexagon")) {
			Shape shape = new HexagonAdapter();
			return shape;
		}

		throw new IllegalStateException("Unexpected Shape found in log file.");
	}
}
