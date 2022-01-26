package it68_2019.oo.drawing;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import it68_2019.oo.geometry.Point;
import it68_2019.oo.geometry.Shape;

public class PnlDrawing extends JPanel {

	private static final long serialVersionUID = 1985287611891040534L;
	private ArrayList<Shape> Shapes = new ArrayList<>();
	private Shape SelectedShape = null;
	private boolean ItemSelected = false;

	public void add(Shape shape) {
		Shapes.add(shape);
		paint(getGraphics());
	}

	public void select(Point point) {
		for (Shape shape : Shapes) {
			shape.setSelected(false);
		}
		ItemSelected = false;
		for (int i = Shapes.size(); i-- > 0;) {
			if (Shapes.get(i).contains(point)) {
				Shapes.get(i).setSelected(true);
				SelectedShape = Shapes.get(i);
				ItemSelected = true;
				break;
			}
		}
		paint(getGraphics());
	}

	@Override
	public void paint(Graphics g) {
		repaint();
		for (Shape shape : Shapes) {
			shape.draw(g);
		}
	}

	public void updateShape(Shape updated) {
		for (int i = Shapes.size(); i-- > 0;) {
			if (Shapes.get(i).isSelected()) {
				Shapes.remove(i);
				Shapes.add(i, updated);
				updated.setSelected(true);
				SelectedShape = Shapes.get(i);
				break;
			}
		}
		ItemSelected = true;
		paint(getGraphics());
	}

	public void clear() {
		int removed = 0;
		for (int i = Shapes.size() - removed; i-- > 0;) {
			Shapes.remove(Shapes.get(i));
			removed++;
		}
		ItemSelected = false;
		paint(getGraphics());
	}

	public void removeSelected() {
		int removed = 0;
		for (int i = Shapes.size() - removed; i-- > 0;) {
			if (Shapes.get(i).isSelected()) {
				Shapes.remove(Shapes.get(i));
				removed++;
			}
		}
		ItemSelected = false;
		paint(getGraphics());
	}

	public boolean isItemSelected() {
		return ItemSelected;
	}

	public Shape getSelectedShape() {
		return SelectedShape;
	}

	public int count() {
		return Shapes.size();
	}

}
