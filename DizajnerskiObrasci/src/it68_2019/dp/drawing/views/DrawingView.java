package it68_2019.dp.drawing.views;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Iterator;

import it68_2019.dp.drawing.models.DrawingModel;
import it68_2019.dp.drawing.models.geometry.Shape;
import java.awt.Color;

public class DrawingView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030623193401339242L;
	private DrawingModel model = new DrawingModel();

	public DrawingView() {
		setBackground(Color.WHITE);

	}

	public void setModel(DrawingModel model) {
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
