package it68_2019.dp.drawing.components.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import it68_2019.dp.drawing.models.geometry.Shape;

public class CanvasView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030623193401339242L;
	private CanvasModel model = new CanvasModel();

	public CanvasView() {
		setBackground(Color.WHITE);

	}

	public void setModel(CanvasModel model) {
		this.model = model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getAllShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

}
