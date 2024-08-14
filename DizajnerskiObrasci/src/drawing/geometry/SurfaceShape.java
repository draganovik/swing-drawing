package drawing.geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {
	private static final long serialVersionUID = -1872909233036323497L;
	private Color backgroundColor = Color.LIGHT_GRAY;

	public abstract void fill(Graphics g);

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void updateFrom(Shape shape) throws Exception {
		if (!(shape instanceof SurfaceShape)) {
			throw new NumberFormatException("Inner radius must be less than radius by at least 3.");
		}
		SurfaceShape surfaceShape = (SurfaceShape) shape;
		super.updateFrom(surfaceShape);
		this.setBackgroundColor(surfaceShape.getBackgroundColor());
	}
}
