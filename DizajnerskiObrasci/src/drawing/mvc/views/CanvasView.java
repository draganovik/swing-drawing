package drawing.mvc.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;

import javax.swing.JPanel;

import drawing.geometry.Shape;
import drawing.mvc.DrawingController;
import drawing.mvc.CanvasModel;

public class CanvasView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6030623193401339242L;
	private DrawingController controller;
	private CanvasModel model = new CanvasModel();

	public CanvasView() {
		setBackground(Color.WHITE);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Enumeration<Shape> e = model.getAllShapesDLM().elements(); e.hasMoreElements();) {
			e.nextElement().draw(g);
		}
	}

	public void setController(DrawingController ccontroller) {
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
