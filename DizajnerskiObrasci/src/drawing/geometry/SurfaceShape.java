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

	public void updateFrom(SurfaceShape surfaceShape) {
		super.updateFrom(surfaceShape);
		this.setBackgroundColor(surfaceShape.getBackgroundColor());
	}
}
