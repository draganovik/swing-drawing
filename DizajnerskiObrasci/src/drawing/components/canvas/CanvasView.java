package drawing.components.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;

import javax.swing.JPanel;

import drawing.geometry.Shape;

public class CanvasView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6030623193401339242L;
	private CanvasController controller;
	private CanvasModel model = new CanvasModel();

	public CanvasView() {
		setBackground(Color.WHITE);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getAllShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

	public void setController(CanvasController ccontroller) {
		this.controller = ccontroller;
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				controller.mouseReleased(e);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controller.mouseDragged(e);
			}
		});

	}

	public void setModel(CanvasModel model) {
		this.model = model;
	}

}
