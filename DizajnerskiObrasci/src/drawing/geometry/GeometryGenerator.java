package drawing.geometry;

import java.awt.Color;

import drawing.adapters.HexagonAdapter;

public class GeometryGenerator {
	public static Shape generate(String line) {

		String shapeName = line.substring(0, line.indexOf('['));

		String shapeParams = line.substring(line.indexOf('[') + 1, line.lastIndexOf(']'));

		if (shapeName.equals(Circle.class.getSimpleName())) {
			Shape shape = parseCircle(shapeParams);
			return shape;
		}

		if (shapeName.equals(Donut.class.getSimpleName())) {
			Shape shape = parseDonut(shapeParams);
			return shape;
		}

		if (shapeName.equals(Line.class.getSimpleName())) {
			Shape shape = parseLine(shapeParams);
			return shape;
		}

		if (shapeName.equals(Point.class.getSimpleName())) {
			Shape shape = parsePoint(shapeParams);
			return shape;
		}

		if (shapeName.equals(Rectangle.class.getSimpleName())) {
			Shape shape = parseRectangle(shapeParams);
			return shape;
		}

		if (shapeName.equals("Hexagon")) {
			Shape shape = parseHexagon(shapeParams);
			return shape;
		}

		throw new IllegalStateException("Unexpected Shape found in log file.");
	}

	private static Color parseColor(String colorString) {
		String[] colors = colorString.split(",");
		int r = Integer.parseInt(colors[0].substring(2));
		int g = Integer.parseInt(colors[1].substring(2));
		int b = Integer.parseInt(colors[2].substring(2));
		return new Color(r, g, b);
	}

	private static Point parsePoint(String pointString) {
		String[] params = pointString.split(pointString.contains(" ") ? ", " : ",");
		String x = params[0].substring(params[0].indexOf("=") + 1);
		String y = params[1].substring(params[1].indexOf("=") + 1);
		if (params.length > 2) {
			String colorString = params[2].substring(params[2].indexOf("[") + 1, params[2].indexOf("]"));
			Color color = parseColor(colorString);
			Boolean isSelected = Boolean.parseBoolean(params[3].substring(params[3].indexOf("=") + 1));
			return new Point(Integer.parseInt(x), Integer.parseInt(y), isSelected, color);
		}
		return new Point(Integer.parseInt(x), Integer.parseInt(y));
	}

	private static Line parseLine(String lineString) {
		String[] params = lineString.split(", ");
		Point startPoint = parsePoint(params[0].substring(params[0].indexOf("[") + 1, params[0].indexOf("]")));
		Point endPoint = parsePoint(params[1].substring(params[1].indexOf("[") + 1, params[1].indexOf("]")));
		String colorString = params[2].substring(params[2].indexOf("[") + 1, params[2].indexOf("]"));
		Color color = parseColor(colorString);
		Boolean isSelected = Boolean.parseBoolean(params[3].substring(params[3].indexOf("=") + 1));

		return new Line(startPoint, endPoint, isSelected, color);
	}

	private static Rectangle parseRectangle(String rectangleString) {
		String pointString = rectangleString.substring(rectangleString.indexOf("[") + 1, rectangleString.indexOf("]"));
		Point point = parsePoint(pointString);
		String[] params = rectangleString.split(", ");
		String width = params[1].substring(params[1].indexOf("=") + 1);
		String height = params[2].substring(params[2].indexOf("=") + 1);
		String colorString = params[3].substring(params[3].indexOf("[") + 1, params[3].indexOf("]"));
		Color color = parseColor(colorString);
		String backgroundString = params[4].substring(params[4].indexOf("[") + 1, params[4].indexOf("]"));
		Color background = parseColor(backgroundString);
		Boolean isSelected = Boolean.parseBoolean(params[5].substring(params[5].indexOf("=") + 1));
		return new Rectangle(point, Integer.parseInt(width), Integer.parseInt(height), isSelected, color, background);
	}

	private static Circle parseCircle(String circleString) {
		String pointString = circleString.substring(circleString.indexOf("[") + 1, circleString.indexOf("]"));
		Point point = parsePoint(pointString);
		String[] params = circleString.split(", ");
		String radius = params[1].substring(params[1].indexOf("=") + 1);
		String colorString = params[2].substring(params[2].indexOf("[") + 1, params[2].indexOf("]"));
		Color color = parseColor(colorString);
		String backgroundString = params[3].substring(params[3].indexOf("[") + 1, params[3].indexOf("]"));
		Color background = parseColor(backgroundString);
		Boolean isSelected = Boolean.parseBoolean(params[4].substring(params[4].indexOf("=") + 1));
		return new Circle(point, Integer.parseInt(radius), isSelected, color, background);
	}

	private static Donut parseDonut(String donutString) {
		String pointString = donutString.substring(donutString.indexOf("[") + 1, donutString.indexOf("]"));
		Point point = parsePoint(pointString);
		String[] params = donutString.split(", ");
		String radius = params[1].substring(params[1].indexOf("=") + 1);
		String innerRadius = params[2].substring(params[2].indexOf("=") + 1);
		String colorString = params[3].substring(params[3].indexOf("[") + 1, params[3].indexOf("]"));
		Color color = parseColor(colorString);
		String backgroundString = params[4].substring(params[4].indexOf("[") + 1, params[4].indexOf("]"));
		Color background = parseColor(backgroundString);
		Boolean isSelected = Boolean.parseBoolean(params[5].substring(params[5].indexOf("=") + 1));
		return new Donut(point, Integer.parseInt(radius), Integer.parseInt(innerRadius), isSelected, color, background);
	}

	private static HexagonAdapter parseHexagon(String hexagonString) {
		String pointString = hexagonString.substring(hexagonString.indexOf("[") + 1, hexagonString.indexOf("]"));
		Point point = parsePoint(pointString);
		String[] params = hexagonString.split(", ");
		String radius = params[1].substring(params[1].indexOf("=") + 1);
		String colorString = params[2].substring(params[2].indexOf("[") + 1, params[2].indexOf("]"));
		Color color = parseColor(colorString);
		String backgroundString = params[3].substring(params[3].indexOf("[") + 1, params[3].indexOf("]"));
		Color background = parseColor(backgroundString);
		Boolean isSelected = Boolean.parseBoolean(params[4].substring(params[4].indexOf("=") + 1));
		return new HexagonAdapter(point, Integer.parseInt(radius), isSelected, color, background);
	}
}
